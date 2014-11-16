package org.blh.statistics.download;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.blh.statistics.download.tor.BlhTor;

public abstract class Downloader {

    protected final UrlsToDownload urlsDb;
    private final Map<Integer, Boolean> threadIdToRunning;
    private final File downloadDir;

    public Downloader() {
        String id = getName().replace(" ", "_").replace("/", "_").trim();

        File database = new File(id + ".db");
        boolean doSeed = !database.exists();

        urlsDb = new UrlsToDownload(database.getName());
        threadIdToRunning = new HashMap<>();

        downloadDir = new File(id + "-downloaded-recipes");
        System.out.println("Saving downloaded recipes in " + downloadDir.getAbsolutePath());
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        if (doSeed) {
            seed();
        }
    }

    protected abstract String getName();

    protected abstract void seed();

    public void gogogo() {
        System.out.println(urlsDb.getNumberOfUrlsToDownload() + " urls to download");
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8).parallel().forEach(this::requestAndDownloadUrls);
    }

    private void requestAndDownloadUrls(int i) {
        System.out.println("Thread " + i + " started!");
        threadIdToRunning.put(i, true);
        BlhTor tor = new BlhTor();

        while (threadIdToRunning.get(i)) {
            try {
                long sleepMillis = (long) (Math.random() * 30000 + 3000);
                System.out.println("  Worker " + i + " is sleeping for " + sleepMillis + "ms");
                Thread.sleep(sleepMillis);

                if (Math.random() < 0.2) {
                    System.out.println("  Worker " + i + " requesting a new tor exit node");
                    tor.useNewExitNodeOnNextRequest();
                }

            } catch (InterruptedException | IOException ex) {
                System.out.println("  Worker " + i + " failed its humanizing procedure    ########################");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

            urlsDb.downloadUrl(url -> downloadUrl(i, url, tor, 0));
        }
        System.out.println("Thread " + i + " done!");
    }

    private void downloadUrl(int workerId, URL url, BlhTor tor, int attempt) {
        if (url == null) {
            threadIdToRunning.put(workerId, false);
            return;
        }

        if (attempt == 3) {
            System.out.println("  Worker " + workerId + " tried to download " + url + ", but failed after " + attempt + " attempts");
            throw new RuntimeException("Tried to download " + url + " " + attempt + " times . I give up");
        }

        try {
            System.out.println("Worker " + workerId + " is downloading " + url);
            URI uri = url.toURI();
            String downloadedHtml = tor.downloadUri(uri);
            File fileLocation = writeToDisk(downloadedHtml, (url.getAuthority() + url.getFile()).replace(File.separator, "__"));
            urlsDb.urlDownloadedTo(url, fileLocation);

            handleDownloadedFile(url, downloadedHtml);
        } catch (URISyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to convert url to uri, lol", ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("  Some worker failed to download " + url + ", trying again");
            try {
                tor.useNewExitNodeOnNextRequest();
            } catch (IOException ex1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to request new exit node", ex1);
                throw new RuntimeException(ex);
            }
            downloadUrl(workerId, url, tor, attempt + 1);
        }
    }

     private File writeToDisk(String contents, String filePath) throws IOException {
        File file = new File(downloadDir, filePath);
        try (FileWriter fw = new FileWriter(file)) {
            fw.append(contents);
            fw.flush();
            System.out.println("  Wrote downloaded html to " + filePath);
            return file;
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    protected abstract void handleDownloadedFile(URL url, String downloadedHtml);
}

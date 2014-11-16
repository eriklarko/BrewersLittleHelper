package org.blh.statistics.download;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.blh.statistics.download.db.DBConnector;
import org.blh.statistics.download.db.DBManager;

/**
 *
 * @author eriklark
 */
public class UrlsToDownload {

    private final String tableName = "urls";
    private final Semaphore numberOfAvailableUrls;
    private final Semaphore tagUrlSem;

    public UrlsToDownload() {
        this("test.db");
    }

    public UrlsToDownload(String databaseName) {
        DBManager.setCredentials("jdbc:sqlite:" + databaseName, null, null, DBConnector.SQLITE);
        try {
            ensureDBStructure();
            resetUnfinishedUrls();

            numberOfAvailableUrls = new Semaphore(getNumberOfUrlsToDownload());
            tagUrlSem = new Semaphore(1);
        } catch (IllegalStateException | SQLException ex) {
            throw new RuntimeException("Unable to open database", ex);
        }
    }

    private void ensureDBStructure() throws SQLException {
        if (!tableExists() || !tableIsValid()) {
            update("DROP TABLE IF EXISTS " + tableName);
            createTable();
        }
    }

    private boolean tableExists() throws SQLException {
        try (ResultSet rs = query("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name")) {
            while (rs.next()) {
                if (rs.getString(1).equals(tableName)) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean tableIsValid() {
        try (ResultSet rs = query("SELECT key, url, started, finished, numberOfTimesEncountered, file FROM " + tableName)) {
            if (rs.next()) {
                rs.getString(1);
                rs.getString(2);
                rs.getString(3);
                rs.getString(4);
                rs.getInt(5);
                rs.getString(6);

                return true;
            }
        } catch (SQLException ex) {
        }

        return false;
    }

    private void createTable() {
        String createQuery = "CREATE TABLE " + tableName + " ("
                + "key TEXT, "
                + "url TEXT, "
                + "started TEXT, "
                + "finished TEXT, "
                + "numberOfTimesEncountered INTEGER, "
                + "file TEXT"
                + ")";
        update(createQuery);
    }

    private void resetUnfinishedUrls() {
        update("update %s set started = null where finished is null", tableName);
    }

    public int getNumberOfUrlsToDownload() {
        try (ResultSet rs = query("select count(*) from %s where started is null", tableName)) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void dumpDb() {
        try {
            Collection<String> tables = new LinkedList<>();
            ResultSet rs = query("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name");
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            rs.close();

            for (String table : tables) {
                System.out.println(" == Structure of " + table + " ==");
                rs = query("SELECT * FROM " + table);
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int column = 1; column <= rsmd.getColumnCount(); column++) {
                    System.out.println(rsmd.getColumnName(column) + ": " + rsmd.getColumnTypeName(column));
                }

                System.out.println("  = CONTENTS =");
                if (rs.next()) {
                    do {
                        for (int column = 1; column <= rsmd.getColumnCount(); column++) {
                            System.out.print(rsmd.getColumnName(column) + ": " + rs.getObject(column) + "\t");
                        }
                        System.out.println("");
                    } while (rs.next());
                } else {
                    System.out.println("empty");
                }
                rs.close();
            }
        } catch (SQLException | IllegalStateException ex) {
            throw new RuntimeException("Unable to dump database", ex);
        }
    }

    private void startedDownloading(URL url) {
        if (!hasEncounteredBefore(url)) {
            insertNewUrl(url);
        }
        update("update %s set started = '%s' where key = '%s'", tableName, getNowAsString(), toDbKey(url));
    }

    private synchronized void update(String sql, Object... parameters) {
        try {
            DBManager.getDBC().update(String.format(sql, parameters));
        } catch (IllegalStateException | SQLException ex) {
            throw new RuntimeException("Unable to update database", ex);
        }
    }

    private String getNowAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    private String toDbKey(URL url) {
        return url.getAuthority() + url.getFile();
    }

    public synchronized void encountered(URL url) {
        if (hasEncounteredBefore(url)) {
            incrementEncounteredCounter(url);
        } else {
            insertNewUrl(url);
        }
        numberOfAvailableUrls.release();
    }

    private boolean hasEncounteredBefore(URL url) {
        try (ResultSet rs = query("select count(*) from %s where key = '%s'", tableName, toDbKey(url))) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Unable to query for encounter database", ex);
        }

        return false;
    }

    private synchronized ResultSet query(String sql, Object... parameters) {
        try {
            return DBManager.getDBC().query(String.format(sql, parameters));
        } catch (IllegalStateException | SQLException ex) {
            throw new RuntimeException("Unable to query database", ex);
        }
    }

    private void incrementEncounteredCounter(URL url) {
        ResultSet rs = query("select numberOfTimesEncountered from %s where key = '%s'", tableName, toDbKey(url));
        try {
            if (rs.next()) {
                int numberOfTimesEncountered = rs.getInt(1);
                update("update %s set numberOfTimesEncountered = %s where key = '%s'", tableName, numberOfTimesEncountered + 1, toDbKey(url));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Unable to increment counter", ex);
        }
    }

    private void insertNewUrl(URL url) {
        update("insert into %s (key, url, started, finished, numberOfTimesEncountered) values ('%s','%s',%s,%s,%s)", tableName, toDbKey(url), url, null, null, 1);
    }

    private void finishedDownloading(URL url) {
        update("update %s set finished = '%s' where key = '%s'", tableName, getNowAsString(), toDbKey(url));
    }

    public void downloadUrl(Consumer<URL> consumer) {
        if (!tryAcquireUrl()) {
            System.out.println("  Some worker could not get a job in the set amount of time");
            return;
        }

        tagUrlSem.acquireUninterruptibly();
        Optional<URL> url = getArbitraryAvailableUrl();
        if (url.isPresent()) {
            startedDownloading(url.get());
            tagUrlSem.release();
            try {
                consumer.accept(url.get());
                finishedDownloading(url.get());
            } catch (Exception ex) {
                numberOfAvailableUrls.release();
                System.out.println("  Some worker failed to consume url " + url + ". " + ex);
            }
        } else {
            tagUrlSem.release();
            // Tell consumer to die gracefully
            consumer.accept(null);
        }
    }

    private boolean tryAcquireUrl() {
        try {
            return numberOfAvailableUrls.tryAcquire(3, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(UrlsToDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Optional<URL> getArbitraryAvailableUrl() {
        try (ResultSet rs = query("select url from %s where started is null limit 1", tableName)) {
            if (rs.next()) {
                String rawUrl = rs.getString(1);
                return Optional.of(new URL(rawUrl));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Unable to get url", ex);
        }
        return Optional.empty();
    }

    public void urlDownloadedTo(URL url, File fileLocation) {
        update("update %s set file = '%s' where key = '%s'", tableName, fileLocation.getAbsolutePath(), toDbKey(url));
    }
}

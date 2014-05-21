package se.angstroms.blh.anders.uncategorized.debug;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Lists classes on the class path.
 *
 * @author Thinner
 */
public final class ClassEnumerator {

    private static void log(String msg) {
        System.out.println("ClassDiscovery: " + msg);
    }

    private static void processDirectory(File directory, String pkgname, ArrayList<String> filesFound) {
        log("Reading Directory " + directory);
        // Get the list of the files contained in the package
        String[] files = directory.list();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            filesFound.add(pkgname + '.' + fileName);
            File subdir = new File(directory, fileName);
            if (subdir.isDirectory()) {
                processDirectory(subdir, pkgname + '.' + fileName, filesFound);
            }
        }
    }

    private static void processJarfile(URL resource, String pkgname, ArrayList<String> filesFound) {
        String resPath = resource.getPath();
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        log("Reading JAR file: " + jarPath);
        JarFile jarFile;
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException reading JAR File " + jarPath, e);
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            log("Processing entry " + entryName);
            filesFound.add(entryName);
        }
    }

    public static Collection<String> getFilesForPackage(Package pkg) {
        ArrayList<String> files = new ArrayList<>();

        String pkgname = pkg.getName();
        String relPath = pkgname.replace('.', '/');

        // Get a File object for the package
        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (resource == null) {
            throw new RuntimeException("Unexpected problem: No resource for " + relPath);
        }
        log("Package: " + pkgname + " becomes Resource: " + resource.toString());

        resource.getPath();
        if (resource.toString().startsWith("jar:")) {
            processJarfile(resource, pkgname, files);
        } else {
            processDirectory(new File(resource.getPath()), pkgname, files);
        }

        return files;
    }

    private ClassEnumerator() {
    }
}

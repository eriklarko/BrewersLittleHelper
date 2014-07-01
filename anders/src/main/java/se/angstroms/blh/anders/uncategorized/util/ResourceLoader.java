package se.angstroms.blh.anders.uncategorized.util;

import se.angstroms.blh.anders.uncategorized.debug.ClassEnumerator;
import java.net.URL;
import java.util.Collection;
import java.util.MissingResourceException;

/**
 * Helper methods to load a resource on the class path.
 *
 * @author Thinner
 */
public final class ResourceLoader {

    public static URL getResource(Class<?> clazz, String resourceLocation) {
        URL resource = clazz.getResource(resourceLocation);
        if (resource == null) {
            throw new MissingResourceException("Unable to find " + resourceLocation + " in the classpath for "
                    + clazz + " (" + clazz.getPackage() + ")", clazz.getName(), resourceLocation);
        }
        return resource;
    }

    public static Collection<String> listObjectsOnClasspath(Package pkg) {
        return ClassEnumerator.getFilesForPackage(pkg);
    }

    private ResourceLoader() {
    }
}

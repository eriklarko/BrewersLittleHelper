package se.angstroms.blh.anders.uncategorized.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Contains helper methods for finding the localized resource bundle and
 * querying it.
 *
 * @author Thinner
 */
public final class ResourceBundleUtil {

    private static ResourceBundle resourceBundle;

    public static ResourceBundle getCurrentResourceBundle() {
        if (resourceBundle == null) {
            try {
                resourceBundle = ResourceBundle.getBundle("i18n.lang", Locale.getDefault());
            } catch (MissingResourceException ex) {
                resourceBundle = ResourceBundle.getBundle("i18n.lang", Locale.US);
            }
        }

        return resourceBundle;
    }

    public static String getString(String key) {
        return getCurrentResourceBundle().getString(key);
    }

    private ResourceBundleUtil() {
    }
}

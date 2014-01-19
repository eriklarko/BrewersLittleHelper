package se.angstroms.blh.anders.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Thinner
 */
public class ResourceBundleUtil {

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
		System.out.println(getCurrentResourceBundle().keySet());
		return getCurrentResourceBundle().getString(key);
	}

	private ResourceBundleUtil() {
	}
}

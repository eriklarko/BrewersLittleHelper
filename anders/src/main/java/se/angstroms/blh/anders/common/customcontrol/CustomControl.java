package se.angstroms.blh.anders.common.customcontrol;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

/**
 * Boilerplate code for creating custom FX-controls
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class CustomControl {

	public static class FXMLLoadException extends RuntimeException {

		public FXMLLoadException() {
		}

		public FXMLLoadException(String message) {
			super(message);
		}

		public FXMLLoadException(String message, Throwable cause) {
			super(message, cause);
		}

		public FXMLLoadException(Throwable cause) {
			super(cause);
		}

		public FXMLLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
	}

	public static void setup(Object instance, String fxmlPath) {
		FXMLLoader fxmlLoader = new FXMLLoader(instance.getClass().getResource(fxmlPath));
		setup(instance, fxmlLoader);
	}

	public static void setup(Object instance, FXMLLoader fxmlLoader) {
		fxmlLoader.setRoot(instance);
		fxmlLoader.setController(instance);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new FXMLLoadException("Unable to load: " + fxmlLoader.getLocation(), exception);
		}
	}

	private CustomControl() {
	}
}

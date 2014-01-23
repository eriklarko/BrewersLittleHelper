package se.angstroms.blh.anders.view.util;

import com.airhacks.afterburner.injection.InjectionProvider;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import se.angstroms.blh.anders.util.ResourceBundleUtil;
import se.angstroms.blh.anders.util.ResourceLoader;

/**
 * Boilerplate code for creating custom FX-controls
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public final class CustomControl {

    /**
     * Thrown if the FXMLLoader was unable to load the resource. It takes the
     * IOException thrown by FXMLLoader and converts in into an unchecked
     * exception.
     */
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

    /**
     * Sets up the controller to use a fxml-file in the same package named as
     * the controller without the word "Controller".
     *
     * Ex. RecipeDetailsController in se.angstroms.anders.recipe.details looks
     * for an fxml-file named RecipeDetails.fxml in
     * se.angstroms.anders.recipe.details
     *
     * @param instance
     */
    public static void setup(Object instance) {
        Class<?> clazz = instance.getClass();
        String className = clazz.getSimpleName();
        String fxmlFileName = className.replace("Presenter", "") + ".fxml";
        setup(instance, fxmlFileName);
    }

    public static void setup(Object instance, String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(ResourceLoader.getResource(instance.getClass(), fxmlPath));
        setup(instance, fxmlLoader);
    }

    public static void setup(Object instance, FXMLLoader fxmlLoader) {
        fxmlLoader.setResources(ResourceBundleUtil.getCurrentResourceBundle());
        InjectionProvider.registerExistingAndInject(instance);
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

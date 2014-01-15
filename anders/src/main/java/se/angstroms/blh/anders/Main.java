package se.angstroms.blh.anders;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ResourceBundle rb;
		try {
			rb = ResourceBundle.getBundle("i18n.lang", Locale.getDefault());
		} catch(MissingResourceException ex) {
			rb = ResourceBundle.getBundle("i18n.lang", Locale.US);
		}

		URL mainWindowFXML = getClass().getResource("MainWindow.fxml");
		Parent root = FXMLLoader.load(mainWindowFXML, rb);

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}

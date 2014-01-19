package se.angstroms.blh.anders;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.angstroms.blh.anders.utils.ResourceBundleUtil;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		URL mainWindowFXML = getClass().getResource("MainWindow.fxml");
		Parent root = FXMLLoader.load(mainWindowFXML, ResourceBundleUtil.getCurrentResourceBundle());

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX applications.
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

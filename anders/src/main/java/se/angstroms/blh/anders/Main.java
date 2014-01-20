package se.angstroms.blh.anders;

import com.airhacks.afterburner.injection.InjectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.angstroms.blh.anders.util.ResourceBundleUtil;
import se.angstroms.blh.anders.util.ResourceLoader;
import se.angstroms.blh.anders.view.mainwindow.MainWindowPresenter;

/**
 * The entrypoint for Anders.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ResourceLoader.getResource(MainWindowPresenter.class, "MainWindow.fxml"),
                ResourceBundleUtil.getCurrentResourceBundle());

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        InjectionProvider.forgetAll();
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

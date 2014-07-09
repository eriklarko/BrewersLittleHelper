package se.angstroms.blh.anders;

import com.airhacks.afterburner.injection.InjectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.inject.Inject;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.formulas.NopFormula;
import se.angstroms.blh.anders.uncategorized.util.ResourceBundleUtil;
import se.angstroms.blh.anders.uncategorized.util.ResourceLoader;
import se.angstroms.blh.anders.context.value.findingformulas.FormulaClasspathScanner;
import se.angstroms.blh.anders.context.value.findingformulas.FormulaClasspathScanner.FormulaFinderException;
import se.angstroms.blh.anders.context.value.findingformulas.FormulaDirectory;
import se.angstroms.blh.anders.view.mainwindow.MainWindowPresenter;

/**
 * This is the entry point for Anders
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Main extends Application {

    @Inject
    private FormulaDirectory formulaDirectory;

    @Inject
    private FormulaClasspathScanner formulaScanner;

    @Override
    public void start(Stage stage) throws Exception {
        printJavaFxVersion();

		InjectionProvider.registerExistingAndInject(this);
		setupEnvironment();

        Parent root = FXMLLoader.load(
            ResourceLoader.getResource(MainWindowPresenter.class, "MainWindow.fxml"),
            ResourceBundleUtil.getCurrentResourceBundle()
        );

        Scene scene = new Scene(root);
		stage.setTitle("Anders");
        stage.setScene(scene);
		stage.setMaximized(true);
        stage.show();
    }

    private void printJavaFxVersion() {
        System.out.println(com.sun.javafx.runtime.VersionInfo.getRuntimeVersion());
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

	private void setupEnvironment() throws FormulaFinderException {
        // TODO: Fix real formulas
        formulaDirectory.register(Value.Id.EXTRACTION_EFFICIENCY, NopFormula.class);

        formulaDirectory.register(Value.Id.PRE_BOIL_VOLUME, NopFormula.class);
        formulaDirectory.register(Value.Id.POST_BOIL_VOLUME, NopFormula.class);
        formulaDirectory.register(Value.Id.PRE_FERMENTATION_VOLUME, NopFormula.class);

        formulaScanner.findAndAddFormulas(formulaDirectory);
	}
}

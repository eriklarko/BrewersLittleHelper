package se.angstroms.blh.anders;

import com.airhacks.afterburner.injection.InjectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.blh.core.unit.Factor;
import org.blh.core.unit.gravity.SpecificGravity;
import se.angstroms.blh.anders.formulas.NopFormula;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.view.util.AndersBuilderFactory;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.FormulaFactory;
import se.angstroms.blh.anders.util.ResourceBundleUtil;
import se.angstroms.blh.anders.uncategorized.ResourceLoader;
import se.angstroms.blh.anders.uncategorized.context.FullContextInitializer;
import se.angstroms.blh.anders.uncategorized.context.InitializerException;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.uncategorized.value.annot.InputtedOrCalculatedValueIndex;
import se.angstroms.blh.anders.uncategorized.value.annot.ValueMappingException;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.FormulaClasspathScanner;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.FormulaClasspathScanner.FormulaFinderException;
import se.angstroms.blh.anders.view.mainwindow.MainWindowPresenter;

/**
 * This is the entry point for Anders
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Main extends Application {

    @Inject
	private FullContext fullContext;

	@Inject
	private FormulaFactory formulaFactory;

    @Inject
    private FullContextInitializer fullContextInitializer;

    @Inject
    private InputtedOrCalculatedValueIndex valueIndex;

    @Inject
    private FormulaClasspathScanner formulaScanner;

    @Override
    public void start(Stage stage) throws Exception {
		InjectionProvider.registerExistingAndInject(this);
		setupEnvironment();


        Parent root = FXMLLoader.load(
                ResourceLoader.getResource(MainWindowPresenter.class, "MainWindow.fxml"),
                ResourceBundleUtil.getCurrentResourceBundle(),
				new AndersBuilderFactory()
        );

        Scene scene = new Scene(root);
		stage.setTitle("Anders");
        stage.setScene(scene);
		stage.setMaximized(true);
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

	private void setupEnvironment() throws InitializerException, ValueMappingException, FormulaFinderException {
		// TODO: Loading indicator. Splash screen?

        // TODO: Remove these two lines
		formulaFactory.register(ValueId.EXTRACTION_EFFICIENCY, new NopFormula<>(new Factor(1), fullContext));
		formulaFactory.register(ValueId.OG, new NopFormula<>(new SpecificGravity(1.050), fullContext));
        formulaScanner.findAndAddFormulas(formulaFactory, fullContext);

        fullContextInitializer.initializeMeEmpty(fullContext);
        valueIndex.buildIndex(fullContext);
	}
}

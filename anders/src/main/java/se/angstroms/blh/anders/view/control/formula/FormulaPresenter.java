package se.angstroms.blh.anders.view.control.formula;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.formulas.Formula;
import org.blh.formuladecorator.formulas.FormulaFactory;
import org.blh.formuladecorator.formulas.NoFormulaFoundException;
import org.blh.formuladecorator.formulas.NoMatchingFormulaFoundException;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class FormulaPresenter extends VBox implements ChangeListener<Formula<? extends Unit<?>>> {

    @FXML
    private Label value;

    @Inject
    private FullContext fullContext;

    private final ObjectProperty<Formula<? extends Unit<?>>> formulaProperty;

    public FormulaPresenter() {
        CustomControl.setup(this);

		formulaProperty = new SimpleObjectProperty<>();
		formulaProperty.addListener(this);
    }

	public Formula<? extends Unit<?>> getFormula() {
        return formulaProperty.get();
    }

    public void setFormula(Formula<? extends Unit<?>> formula) {
        formulaProperty.set(formula);
    }

    public ObjectProperty<Formula<? extends Unit<?>>> forumlaProperty() {
        return formulaProperty;
    }

    public void refresh() {
		Formula<? extends Unit<?>> selectedFormula = formulaProperty.get();
		if (selectedFormula != null) {
			Unit<?> calculatedValue = selectedFormula.calc(fullContext);
			this.value.setText(calculatedValue.toString());
		}
    }

    @Override
    public void changed(ObservableValue<? extends Formula<? extends Unit<?>>> ov,
            Formula<? extends Unit<?>> oldValue,
            Formula<? extends Unit<?>> newValue) {
		refresh();
    }

	@FXML
    private void onValueClicked() {
        System.out.println("Blurred the liiiiiines!");
    }
}

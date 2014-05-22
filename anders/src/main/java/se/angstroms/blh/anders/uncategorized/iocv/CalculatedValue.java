package se.angstroms.blh.anders.uncategorized.iocv;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;

/**
 *
 * @author eriklark
 */
public class CalculatedValue<T extends Unit<?>> {

    private final ObjectProperty<ObservableFormula<T>> formulaProperty;

    public CalculatedValue(ObservableFormula<T> formula) {
        this.formulaProperty = new SimpleObjectProperty<>(formula);
    }

    public ObjectProperty<ObservableFormula<T>> formulaProperty() {
        return formulaProperty;
    }

    public void addFormulaListener(InvalidationListener invalidationListener) {
        formulaProperty.get().addListener(invalidationListener);
    }

    public void removeFormulaListener(InvalidationListener invalidationListener) {
        formulaProperty.get().removeListener(invalidationListener);
	}
}

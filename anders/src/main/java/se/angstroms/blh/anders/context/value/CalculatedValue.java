package se.angstroms.blh.anders.context.value;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;

/**
 *
 * @author eriklark
 */
public class CalculatedValue<T extends Unit<?>> implements Value<T> {

    private final ObjectProperty<ObservableFormula<T>> formulaProperty;

    public CalculatedValue() {
        this.formulaProperty = new SimpleObjectProperty<>();
    }

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
        if (formulaProperty.get() != null) {
            formulaProperty.get().removeListener(invalidationListener);
        }
    }

    @Override
    public T get() {
        return formulaProperty.get().calc();
    }
}

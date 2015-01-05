package se.angstroms.blh.anders.context.value;

import java.util.Objects;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.formulas.ObservableFormula;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author eriklark
 */
public class CalculatedValue<T extends Unit<?>> implements Value<T> {

    private final ObjectProperty<ObservableFormula<T>> formulaProperty;
	private final Id valueType;

    public CalculatedValue(Id valueType) {
        this(valueType, null);
    }

    public CalculatedValue(Id valueType, ObservableFormula<T> formula) {
		this.valueType = valueType;
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

	@Override
	public Id getValueType() {
		return valueType;
	}

	@Override
	public void addListener(InvalidationListener listener) {
		Objects.nonNull(formulaProperty.get());
		formulaProperty.get().addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		Objects.nonNull(formulaProperty.get());
		formulaProperty.get().removeListener(listener);
	}
}

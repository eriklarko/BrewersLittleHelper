package org.blh.formuladecorator;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.formulas.ObservableFormula;

/**
 *
 * @author Thinner
 * @param <T>
 */
public class InputtedOrCalculatedValue<T extends Unit<?>> {

    private final ReadOnlyObjectWrapper<T> value;
    private final ReadOnlyBooleanWrapper isInputted;
	private final ReadOnlyObjectWrapper<ObservableFormula<T>> formula;
	private final InvalidationListener formulaInvalidationListener = new InvalidationListener() {

			@Override
			public void invalidated(Observable o) {
				value.set(formula.get().calc());
			}
		};

    private InputtedOrCalculatedValue() {
        value = new ReadOnlyObjectWrapper<>();
        isInputted = new ReadOnlyBooleanWrapper();
		formula = new ReadOnlyObjectWrapper();
    }

	public InputtedOrCalculatedValue(T value) {
		this();
		setValue(value);
	}

	public InputtedOrCalculatedValue(ObservableFormula<T> formula) {
		this();
		calculateUsing(formula);
	}

    public ReadOnlyBooleanProperty isInputtedProperty() {
        return isInputted.getReadOnlyProperty();
    }

    public boolean isInputted() {
        return isInputted.get();
    }

	public T value() {
		if (value.get() == null && !this.isInputted()) {
			T calculatedValue = InputtedOrCalculatedValue.this.formula.get().calc();
			value.set(calculatedValue);
		}
		return value.get();
	}

    public ReadOnlyObjectProperty<T> valueProperty() {
        return value.getReadOnlyProperty();
    }

    public final void setValue(T value) {
		assertValueNotNull(value);
		removeFormulaListener();

        this.isInputted.set(true);
        this.value.set(value);
    }

	private void assertValueNotNull(T value) throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("Cannot set value to null");
		}
	}

	private void removeFormulaListener() {
		if (this.formula.get() != null) {
			this.formula.get().removeListener(formulaInvalidationListener);
		}
	}

    public final void calculateUsing(ObservableFormula<T> formula) {

        this.isInputted.set(false);
		this.formula.set(formula);
		formula.addListener(formulaInvalidationListener);
    }
}
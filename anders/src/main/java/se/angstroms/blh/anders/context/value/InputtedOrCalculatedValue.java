package se.angstroms.blh.anders.context.value;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.formulas.ObservableFormula;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 *
 * @author Thinner
 * @param <T>
 */
public class InputtedOrCalculatedValue<T extends Unit<?>> implements Value<T> {

	public static enum STATE {
        INVALID, INPUTTED, CALCULATED;
    }

    private final ReadOnlyObjectWrapper<STATE> state = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyObjectWrapper<T> value = new ReadOnlyObjectWrapper<>();
    private final CalculatedValue<T> calculatedValue;
    private final InputtedValue<T> inputtedValue;

    public InputtedOrCalculatedValue(Id valueType) {
        this.state.set(STATE.INVALID);
        calculatedValue = new CalculatedValue<>(valueType);
        inputtedValue = new InputtedValue<>(valueType);
    }

	public InputtedOrCalculatedValue(Id valueType, ObservableFormula<T> formula) {
        inputtedValue = new InputtedValue<>(valueType);
		calculatedValue = new CalculatedValue<>(valueType, formula);

        this.state.set(STATE.CALCULATED);
        this.calculatedValue.addFormulaListener(this::calculateAndSetValue);
	}

	public InputtedOrCalculatedValue(Id valueType, T value, ObservableFormula<T> formula) {
        inputtedValue = new InputtedValue<>(valueType, value);
        calculatedValue = new CalculatedValue<>(valueType, formula);

        set(value);
	}

    public ReadOnlyObjectProperty<STATE> stateProperty() {
        return state.getReadOnlyProperty();
    }

    @Override
	public T get() {
		if (value.get() == null && this.state.get() == STATE.CALCULATED) {
			calculateAndSetValue(null);
		}
		return value.get();
	}

    private void calculateAndSetValue(Observable ONLY_HERE_FOR_METHOD_TO_BE_A_VALID_LISTENER) {
        if (value.isBound()) {
            throw new RuntimeException(this + "'s value was bound.");
        }

        T newValue = this.calculatedValue.formulaProperty().get().calc();
        value.set(newValue);
    }

    public ReadOnlyObjectProperty<T> valueProperty() {
        return value.getReadOnlyProperty();
    }

    public final void set(T value) {
		assertValueNotNull(value);
        if (state.get() != STATE.INPUTTED) {
            this.calculatedValue.removeFormulaListener(this::calculateAndSetValue);
            this.inputtedValue.set(value);
            this.state.set(STATE.INPUTTED);
            this.value.bind(this.inputtedValue);
            System.out.println("Bound " + this);
        } else {
            this.inputtedValue.set(value);
        }
    }

	private void assertValueNotNull(T value) throws NullPointerException {
		if (value == null) {
			throw new NullPointerException("Cannot set value to null");
		}
	}

    public void enterCalculatedState() {
        if (this.calculatedValue.formulaProperty().get() == null) {
            throw new IllegalStateException("Cannot enter calculated state without a formula");
        }

        doEnterCalculatedState();
        calculateAndSetValue(null);
    }

    private void doEnterCalculatedState() {
        this.value.unbind();
        this.state.set(STATE.CALCULATED);
        this.calculatedValue.addFormulaListener(this::calculateAndSetValue);
    }

    public void setFormula(ObservableFormula<T> formula) {
        this.calculatedValue.formulaProperty().set(formula);
        if (state.get() == STATE.INVALID) {
            doEnterCalculatedState();
        }
    }

    public ReadOnlyObjectProperty<ObservableFormula<T>> formulaProperty() {
        return this.calculatedValue.formulaProperty();
    }

	@Override
	public Id getValueType() {
		// Can be either inputtedValue of calculatedValue, they will always have the same type;
		return inputtedValue.getValueType();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		value.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		value.removeListener(listener);
	}
}
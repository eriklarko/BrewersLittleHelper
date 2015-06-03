package org.blh.value;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formulas.ObservableFormula;

import java.util.Objects;

/**
 * Should be observable
 * Should automatically transition between calculated and inputted
 */
public class InputtedOrCalculatedValue<T extends Unit<?>> extends SimpleObjectProperty<T> {

    static enum State {
        CALCULATED, INPUTTED
    }

    private final Property<State> state = new SimpleObjectProperty<>();
    private final Property<ObservableFormula<T>> formula = new SimpleObjectProperty<>();

    public InputtedOrCalculatedValue(T initialValue) {
        set(initialValue);
    }

    public InputtedOrCalculatedValue(ObservableFormula<T> formula) {
        setFormula(formula);
    }

    @Override
    public void setValue(T newValue) {
        this.set(newValue);
    }

    @Override
    public void set(T newValue) {
        Objects.requireNonNull(newValue);
        state.setValue(State.INPUTTED);
        super.set(newValue);
    }

    public void setFormula(ObservableFormula<T> formula) {
        Objects.requireNonNull(formula);
        state.setValue(State.CALCULATED);
        this.formula.setValue(formula);
        super.set(formula.calc());
    }

    public State getState() {
        return state.getValue();
    }

    public ObservableValue<State> stateProperty() {
        return state;
    }
}
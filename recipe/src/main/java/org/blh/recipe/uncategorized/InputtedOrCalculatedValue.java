package org.blh.recipe.uncategorized;

import org.blh.core.unit.Unit;
import org.blh.recipe.formulas.Formula;

/**
 * Represents a value that is either inputted by the user or calculated by a
 * formula.
 *
 * @author thinner
 */
public class InputtedOrCalculatedValue<T extends Unit<?>> {

    private T value;
    private Formula<T> formula;
    private FullContext context;
    private boolean isInputted;

    public InputtedOrCalculatedValue(T value) {
        if (value == null) {
            throw new NullPointerException("Cannot instantiate with null value, "
                    + "please use the constructor with Formula<T> and FullContext");
        }
        this.value = value;
        this.isInputted = true;
    }

    public InputtedOrCalculatedValue(Formula<T> formula, FullContext context) {
        this.formula = formula;
        this.context = context;

        this.value = null;
        this.isInputted = false;
    }

    public boolean isInputted() {
        return isInputted;
    }

    public T value() {
        if (value == null) {
            value = formula.calc(context);
        }
        return value;
    }

    public void setValue(T value) {
        if (value == null) {
            throw new NullPointerException("Cannot set value to null");
        }

        this.isInputted = true;
        this.formula = null;
        this.context = null;
        this.value = value;
    }
}

package org.blh.formuladecorator;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.formulas.Formula;

/**
 *
 * @author Thinner
 */
public class NewIOCV<T extends Unit<?>> {

    private final ReadOnlyObjectWrapper<T> value;
    private final FullContext context;
    private final ReadOnlyBooleanWrapper isInputted;

    public NewIOCV(FullContext context) {
        this.context = context;
        value = new ReadOnlyObjectWrapper<>();
        isInputted = new ReadOnlyBooleanWrapper();
    }

    public ReadOnlyBooleanProperty getIsInputtedProperty() {
        return isInputted.getReadOnlyProperty();
    }

    public boolean isInputted() {
        return isInputted.get();
    }

    public ReadOnlyObjectProperty<T> getValueProperty() {
        return value.getReadOnlyProperty();
    }

    public void setValue(T value) {
        if (value == null) {
            throw new NullPointerException("Cannot set value to null");
        }

        this.isInputted.set(true);
        this.value.set(value);
    }

    public void calculateUsing(Formula<T> formula) {
        this.isInputted.set(false);
        this.value.set(formula.calc(context));
    }
}

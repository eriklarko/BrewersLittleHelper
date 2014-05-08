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
    private final ReadOnlyBooleanWrapper isInputted;
	private final ReadOnlyObjectWrapper<Class<T>> type;
	private final ReadOnlyObjectWrapper<Formula<T>> formula;
    private final FullContext context;

    public NewIOCV(FullContext context, Class<T> clazz) {
        this.context = context;
		this.type = new ReadOnlyObjectWrapper<>(clazz);

        value = new ReadOnlyObjectWrapper<>();
        isInputted = new ReadOnlyBooleanWrapper();
		formula = new ReadOnlyObjectWrapper();
    }

	public NewIOCV(FullContext context, Class<T> clazz, T value) {
		this(context, clazz);
		setValue(value);
	}

	public NewIOCV(FullContext context, Class<T> clazz, Formula<T> formula) {
		this(context, clazz);
		calculateUsing(formula);
	}

    public ReadOnlyBooleanProperty getIsInputtedProperty() {
        return isInputted.getReadOnlyProperty();
    }

    public boolean isInputted() {
        return isInputted.get();
    }

	public T value() {
		if (this.isInputted()) {
			return value.get();
		} else {
			T calculatedValue = this.formula.get().calc(context);
			if (!calculatedValue.equals(value.get())) {
				value.set(calculatedValue);
			}
			return value.get();
		}
	}

    public ReadOnlyObjectProperty<T> getValueProperty() {
        return value.getReadOnlyProperty();
    }

	public ReadOnlyObjectProperty<Class<T>> getTypeProperty() {
		return type.getReadOnlyProperty();
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
		this.formula.set(formula);
    }
}

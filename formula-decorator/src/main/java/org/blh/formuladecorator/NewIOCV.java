package org.blh.formuladecorator;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectPropertyBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.formulas.ObservableFormula;

/**
 *
 * @author Thinner
 */
public class NewIOCV<T extends Unit<?>> implements ObservableValue<T> {

    private final ReadOnlyObjectWrapper<T> value;
    private final ReadOnlyBooleanWrapper isInputted;
	private final ReadOnlyObjectWrapper<Class<T>> type;
	private final ReadOnlyObjectWrapper<ObservableFormula<T>> formula;

    private NewIOCV(Class<T> clazz) {
		this.type = new ReadOnlyObjectWrapper<>(clazz);

        value = new ReadOnlyObjectWrapper<>();
        isInputted = new ReadOnlyBooleanWrapper();
		formula = new ReadOnlyObjectWrapper();
    }

	public NewIOCV(Class<T> clazz, T value) {
		this(clazz);
		setValue(value);
	}

	public NewIOCV(Class<T> clazz, ObservableFormula<T> formula) {
		this(clazz);
		calculateUsing(formula);
	}

    public ReadOnlyBooleanProperty getIsInputtedProperty() {
        return isInputted.getReadOnlyProperty();
    }

    public boolean isInputted() {
        return isInputted.get();
    }

	public T value() {
		if (value.get() == null && !this.isInputted()) {
			T calculatedValue = NewIOCV.this.formula.get().calc();
			value.set(calculatedValue);
		}
		return value.get();
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

    public void calculateUsing(ObservableFormula<T> formula) {
        this.isInputted.set(false);
		this.formula.set(formula);

		formula.valueProperty().addListener(new ChangeListener<T>() {

			@Override
			public void changed(ObservableValue<? extends T> ov, T oldValue, T newValue) {
				System.out.println("NewIOCV updated!");
				NewIOCV.this.value.set(newValue);
			}
		});
    }

	@Override
	public void addListener(ChangeListener<? super T> cl) {
		this.value.addListener(cl);
	}

	@Override
	public void removeListener(ChangeListener<? super T> cl) {
		this.value.removeListener(cl);
	}

	@Override
	public T getValue() {
		return value();
	}

	@Override
	public void addListener(InvalidationListener il) {
		this.value.addListener(il);
	}

	@Override
	public void removeListener(InvalidationListener il) {
		this.value.removeListener(il);
	}
}
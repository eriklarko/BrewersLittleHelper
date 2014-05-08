package org.blh.formuladecorator.formulas;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.FullContext;

/**
 *
 * @author eriklark
 */
public abstract class ObservableFormula<T extends Unit<?>> {

	private final FullContext context;
	private final ReadOnlyObjectWrapper<T> valueProperty;
	private final ChangeListener<Object> onRegisteredVariableChanged = new ChangeListener<Object>() {

		@Override
		public void changed(ObservableValue<? extends Object> ov, Object t, Object t1) {
			recalculate();
		}
	};

	public ObservableFormula(FullContext context) {
		this.context = context;

		this.valueProperty = new ReadOnlyObjectWrapper<>();

		registerDependentVariables(context);
	}

	public T value() {
		return valueProperty.get();
	}

	public ReadOnlyObjectProperty<T> valueProperty() {
		return valueProperty.getReadOnlyProperty();
	}

	protected abstract void registerDependentVariables(FullContext context);

	protected final void registerDependentVariable(ObservableValue<?> variable) {
		variable.addListener(onRegisteredVariableChanged);
	}

	private void recalculate() {
		System.out.println("Updating value for " + this.getClass());
		valueProperty.set(this.calc());
	}

	public abstract T calc();

	protected FullContext getContext() {
		return context;
	}

    public abstract String getSomeMathLangRepresentation();
}
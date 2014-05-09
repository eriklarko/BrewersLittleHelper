package org.blh.formuladecorator.formulas;

import javafx.beans.property.ReadOnlyObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.InputtedOrCalculatedValue;

/**
 *
 * @author eriklark
 */
public abstract class ObservableFormula<T extends Unit<?>> extends ReadOnlyObjectPropertyBase<T> {

	private final FullContext context;
	private T value;
	private final ChangeListener<Object> onRegisteredVariableChanged = new ChangeListener<Object>() {

		@Override
		public void changed(ObservableValue<? extends Object> ov, Object t, Object t1) {
			recalculate();
		}
	};

	public ObservableFormula(FullContext context) {
		this.context = context;

		registerDependentVariables(context);
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public Object getBean() {
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

	protected abstract void registerDependentVariables(FullContext context);

	protected final void registerDependentVariable(InputtedOrCalculatedValue<?> variable) {
		registerDependentVariable(variable.valueProperty());
	}

	protected final void registerDependentVariable(ObservableValue<?> variable) {
		variable.addListener(onRegisteredVariableChanged);
	}

	private void recalculate() {
		value = this.calc();
		fireValueChangedEvent();
	}

	public abstract T calc();

	protected FullContext getContext() {
		return context;
	}

    public abstract String getSomeMathLangRepresentation();
}
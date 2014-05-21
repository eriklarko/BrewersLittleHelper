package org.blh.formuladecorator.formulas;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import org.blh.formuladecorator.ObservableHelper;

/**
 *
 * @author eriklark
 */
public abstract class ObservableFormula<T extends Unit<?>> implements Observable{

	private final FullContext context;
	private ObservableHelper helper;
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

	protected abstract void registerDependentVariables(FullContext context);

	protected final void registerDependentVariable(InputtedOrCalculatedValue<?> variable) {
		registerDependentVariable(variable.valueProperty());
	}

	protected final void registerDependentVariable(ObservableValue<?> variable) {
		variable.addListener(onRegisteredVariableChanged);
	}

	public abstract T calc();

	protected FullContext getContext() {
		return context;
	}

	@Override
	public void addListener(InvalidationListener il) {
		helper = ObservableHelper.addListener(helper, this, il);
	}

	@Override
	public void removeListener(InvalidationListener il) {
		helper = ObservableHelper.removeListener(helper, il);
	}

	private void recalculate() {
        ObservableHelper.fireEvent(helper);
	}

    public abstract String getSomeMathLangRepresentation();
}
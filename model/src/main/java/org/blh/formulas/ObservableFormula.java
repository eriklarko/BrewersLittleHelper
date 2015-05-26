package org.blh.formulas;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.blh.ObservableHelper;
import org.blh.context.FullContext;
import org.blh.core.unit.Unit;
import org.slf4j.LoggerFactory;

/**
 * A formula whose value automatically updates when a value it depends on is
 * updated. To allow for this the implementations must register which
 * dependencies cause the value of the formula to change. This is done via the
 * registerDependentVariable method. It can be done in the constructor, but
 * to keep all methods short they should be added in the invocation of
 * registerDependentVariables.
 *
 * @author eriklark
 * @param <T> The type of the value calculated by the formula
 */
public abstract class ObservableFormula<T extends Unit<?>> implements Observable{

	private final FullContext context;
	private ObservableHelper helper;

	public ObservableFormula(FullContext context) {
		this.context = context;

		registerDependentVariables(context);
	}

	protected abstract void registerDependentVariables(FullContext context);

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
        if (helper == null) {
			LoggerFactory.getLogger(this.getClass()).debug(getName() + " changed but no one listened, formula instance: " + this);
        } else {
            ObservableHelper.fireEvent(helper);
        }
	}

	public abstract String getName();

	public abstract String getMathRepresentation();

	public abstract String getDescription();
}
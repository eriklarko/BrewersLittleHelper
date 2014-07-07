package se.angstroms.blh.anders.formulas;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.util.ObservableHelper;

/**
 * A formula whose value automatically updates when a value it depends on is
 * updated. To allow for this the implementations must register which
 * dependencies cause the value of the formula to change. This is done via the
 * registerDependentVariable method. It can be done in the constructor, but
 * to keep all methods short they should be added in the invocation of
 * registerDependentVariables.
 *
 * @author eriklark
 */
public abstract class ObservableFormula<T extends Unit<?>> implements Observable{

	private final FullContext context;
	private ObservableHelper helper;
	private final InvalidationListener onRegisteredVariableChanged = new InvalidationListener() {

        @Override
        public void invalidated(Observable o) {
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

    protected final void registerDependentVariable(Observable variable) {
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
        if (helper == null) {
            System.out.println("A formula changed but no one listened");
        } else {
            ObservableHelper.fireEvent(helper);
        }
	}
}
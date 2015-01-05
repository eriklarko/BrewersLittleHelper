package se.angstroms.blh.anders.formulas;

import java.util.Collection;
import java.util.LinkedList;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.uncategorized.util.ObservableHelper;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

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
    private final Collection<Value<?>> dependencies = new LinkedList<>();
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

	protected final void registerDependentVariable(Value<?> variable) {
		variable.addListener(onRegisteredVariableChanged);
        dependencies.add(variable);
	}

    protected final <T> void registerDependentVariable(ObservableList<T> variable, Value.Id valueType) {
        variable.addListener(onRegisteredVariableChanged);
        dependencies.add(new Value<ObservableList<T>>() {

			@Override
			public Value.Id getValueType() {
				return valueType;
			}

			@Override
			public ObservableList<T> get() {
				return variable;
			}

			@Override
			public void addListener(InvalidationListener listener) {
				variable.addListener(listener);
			}

			@Override
			public void removeListener(InvalidationListener listener) {
				variable.removeListener(listener);
			}
		});
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

    public Collection<Value<?>> getDependencies() {
        return dependencies;
    }
}
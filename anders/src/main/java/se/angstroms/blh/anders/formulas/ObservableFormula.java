package se.angstroms.blh.anders.formulas;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.ObservableHelper;

/**
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

    public abstract String getSomeMathLangRepresentation();
}
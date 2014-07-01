package se.angstroms.blh.anders.formulas;

import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.context.FullContext;

/**
 *
 * @author eriklark
 */
public class NopFormula<T extends Unit<?>> extends ObservableFormula<T> {

    private final T val;

    public NopFormula(FullContext context) {
        this(null, context);
    }

    public NopFormula(T val, FullContext context) {
        super(context);
        this.val = val;
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
    }

    @Override
    public T calc() {
        return val;
    }
}

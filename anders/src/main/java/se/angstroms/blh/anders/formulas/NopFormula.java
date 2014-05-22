package se.angstroms.blh.anders.formulas;

import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.uncategorized.context.FullContext;

/**
 *
 * @author eriklark
 */
public class NopFormula<T extends Unit<?>> extends ObservableFormula<T> {

    private final T val;

    public NopFormula(T val, FullContext context) {
        super(context);
        this.val = val;
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
        //registerDependentVariable(context.getAlcoholContent());
    }

    @Override
    public T calc() {
        return val;
    }

    @Override
    public String getSomeMathLangRepresentation() {
        return "1+1";
    }
}

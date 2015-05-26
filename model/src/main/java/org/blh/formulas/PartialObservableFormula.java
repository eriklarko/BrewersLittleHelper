package org.blh.formulas;

import org.blh.context.FullContext;
import org.blh.core.unit.Unit;

/**
 * Represents a partial formula, or a formula that does not yet know how to get
 * it's variables.
 * 
 * @author eriklark
 */
public class PartialObservableFormula<T extends Unit<?>> {

    private final Class<ObservableFormula<T>> formula;

    public PartialObservableFormula(Class<ObservableFormula<T>> formula) {
        this.formula = formula;
    }

    public ObservableFormula<T> bindTo(FullContext context) {
        try {
            return formula.getConstructor(FullContext.class).newInstance(context);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to build formula of class " + formula, ex);
        }
    }
}

package se.angstroms.blh.anders.formulas.observable;

import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.context.FullContext;

/**
 *
 * @author eriklark
 */
public class ObservableFormulaBuilder<T extends Unit<?>> {

    private final Class<ObservableFormula<T>> formula;

    public ObservableFormulaBuilder(Class<ObservableFormula<T>> formula) {
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

package blh.core.formulas.gravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.ExtractPotential;

/**
 *
 * @author thinner
 */
public class ExtractPotentialFormula implements Formula<ExtractPotential>{

    @Override
    public ExtractPotential calc(FullContext context) {
        return context.getRecipe().getTotalExtractPotential();
    }

}

package blh.core.formulas.weights;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class TotalGrainWeight implements Formula<Kilograms>{

    @Override
    public Kilograms calc(FullContext context) {
        return context.getIngredientsList().getTotalGrainWeight();
    }
}

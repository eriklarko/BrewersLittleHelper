package blh.core.formulas.weights;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.weight.Grams;

/**
 *
 * @author thinner
 */
public class TotalHopWeight implements Formula<Grams>{

    @Override
    public Grams calc(FullContext context) {
        return context.getIngredientsList().getTotalHopWeight();
    }
}
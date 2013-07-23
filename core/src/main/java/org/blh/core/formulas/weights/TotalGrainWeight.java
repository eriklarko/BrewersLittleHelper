package org.blh.core.formulas.weights;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.weight.Kilograms;

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

package org.blh.core.formulas.weights;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.weight.Grams;

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
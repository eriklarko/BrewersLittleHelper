package org.blh.core.formulas.color;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.color.ColorPotential;

/**
 *
 * @author thinner
 */
public class ColorPotentialFormula implements Formula<ColorPotential>{

    @Override
    public ColorPotential calc(FullContext context) {
        return context.getIngredientsList().getTotalColorPotential();
    }

}

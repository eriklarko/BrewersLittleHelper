package blh.core.formulas.color;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.color.ColorPotential;

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

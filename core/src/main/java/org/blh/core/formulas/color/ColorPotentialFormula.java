package org.blh.core.formulas.color;

import org.blh.core.recipe.GristPart;
import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.weight.Lbs;

/**
 *
 * @author thinner
 */
public class ColorPotentialFormula {

    public ColorPotential calc(Iterable<GristPart> fermentables) {
        ColorPotential potential = new ColorPotential();
        for (GristPart fermentable : fermentables) {
            potential.add(fermentable.getMalt().getColor(), new Lbs(fermentable.getAmount()));
        }
        return potential;
    }

}

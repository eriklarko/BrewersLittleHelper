package org.blh.core.formula.color;

import org.blh.core.recipe.GristPart;
import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.weight.Lbs;

/**
 * Calculates the color potential for a grist.
 *
 * TODO: Find source for this forumla.
 *
 * @author thinner
 */
public class ColorPotentialFormula {

    public ColorPotential calc(Iterable<GristPart> fermentables) {
        ColorPotential potential = new ColorPotential();
        for (GristPart fermentable : fermentables) {
            potential = potential.add(fermentable.getMalt().getColor(), new Lbs(fermentable.getAmount()));
        }
        return potential;
    }

}

package blh.core.formulas.gravity.originalgravity;

import blh.core.formulas.Formula;
import blh.core.ingredients.Malt;
import blh.core.recipe.GristPart;
import blh.core.uncategorized.FullContext;
import blh.core.units.Factor;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.volume.Liters;

import java.util.List;

/**
 * Taken from http://brewgr.com/calculations/original-gravity
 *
 * Original Gravity = Amount of Extract * PPG / Batch Size
 *                  =
 *
 * Created by Erik Larkö at 7/2/13 11:27 PM
 */
public class Brewgr implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        return calc(context.getIngredientsList().getFermentables(), context.extractionEfficiency.value(), context.finalVolume.value());
    }

    private SpecificGravity calc(List<GristPart> fermentables, Factor extractionEfficiency, Liters batchSize) {
        double nominator = 0;
        for(GristPart fermentable : fermentables) {
            double t =   fermentable.getAmount().value() * fermentable.getMalt().extractPotential.value();

            if(fermentable.getMalt().type == Malt.TYPE.GRAIN) {
                t = t * extractionEfficiency.value();
            }

            nominator += t;
        }

        return new SpecificGravity(nominator / batchSize.value());
    }
}

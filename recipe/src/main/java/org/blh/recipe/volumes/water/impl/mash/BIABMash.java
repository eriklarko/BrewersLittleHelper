package org.blh.recipe.volumes.water.impl.mash;

import org.blh.core.units.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;
import org.blh.recipe.volumes.water.BrewStep;

/**
 * In BIAB, water get stuck in the grain. When the bag is removed, so is a lot
 * of water. 90% of the total grain weight is said to be lost.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 9:58:56 PM
 */
public class BIABMash extends BrewStep {

    @Override
    protected Liters calculateVolumeAfterStep(Liters volumeBeforeStep, FullContext context) {
        double litersStuckInGrain = context.getRecipe().getIngredientsList().getTotalGrainWeight().value() * 0.9;
        double litersLeftAfterBagIsRemoved = volumeBeforeStep.value() - litersStuckInGrain;

        return new Liters(litersLeftAfterBagIsRemoved);
    }

    @Override
    protected Liters calculateVolumeBeforeStep(Liters volumeAfterStep, FullContext context) {
        double litersStuckInGrain = context.getRecipe().getIngredientsList().getTotalGrainWeight().value() * 0.9;
        double litersInPotWhenBagIsIn = volumeAfterStep.value() + litersStuckInGrain;

        return new Liters(litersInPotWhenBagIsIn);
    }

}

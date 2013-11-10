package org.blh.core.formulas.bitterness;

import org.blh.core.formulas.Formula;
import org.blh.core.recipe.HopAddition;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Grams;

/**
 * Taken from http://realbeer.com/hops/FAQ.html
 * 
 * IBU = (GRAMS OF HOPS) * %UTILIZATION * %ALPHA * 1000 / VOLUME(litres) * (1 +
 * GA)
 * 
 * %UTILIZATION = 18.11 + 13.86 * hyptan[(MINUTES - 31.32) / 18.27]
 * 
 * GA if BOIL_GRAVITY > 1.050 = (BOIL_GRAVITY - 1.050) / 0.2
 * GA otherwise = 0
 *
 * @author thinner
 */
public class Rager implements Formula<IBU> {

    @Override
    public IBU calc(FullContext context) {
        double totalIBUs = 0;
        for (HopAddition addition : context.getIngredientsList().getHopAdditions()) {
            totalIBUs += getRawIBUsFromAddition(addition,
                    context.getBoilVolumeAtMinutesLeft(addition.getTimeInBoil()),
                    context.getBoilGravityAtMinutesLeft(addition.getTimeInBoil()));
        }
        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(HopAddition addition, Liters volume, SpecificGravity boilGravity) {
        Grams amount = addition.getAmount();
        double utilization = getUtilization(addition.getTimeInBoil()) / 100;
        double alphaAcids = addition.getHop().alphaAcids.asFactor().inexactValue();
        double gravityAdjustment = getGravityAdjustment(boilGravity);
        
        double IBUs = (amount.inexactValue() * utilization * alphaAcids * 1000);
        IBUs = IBUs / (volume.inexactValue() * (1 + gravityAdjustment));

        return IBUs;
    }

    private double getUtilization(Minutes timeInBoil) {
        return 18.11 + 13.86 * Math.tanh((timeInBoil.inexactValue() - 31.32) / 18.27);
    }

    private double getGravityAdjustment(SpecificGravity boilGravity) {
        double sg = boilGravity.inexactValue();
        return (sg > 1.050) ? (sg - 0.050) / 0.2 : 0;
    }

    public IBU getIBUsFromAddition(HopAddition addition, Liters volume, SpecificGravity boilGravity) {
        return new IBU(getRawIBUsFromAddition(addition, volume, boilGravity));
    }
}

package org.blh.core.formula.bitterness;

import org.blh.core.recipe.HopAddition;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Grams;

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
public class Rager  {

    public IBU calc(Iterable<HopAddition> hopAdditions, Liters currentBoilVolume, SpecificGravity currentBoilGravity) {
        double totalIBUs = 0;
        for (HopAddition addition : hopAdditions) {
            totalIBUs += getRawIBUsFromAddition(addition, currentBoilVolume, currentBoilGravity);
        }
        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(HopAddition addition, Liters volume, SpecificGravity boilGravity) {
        Grams amount = addition.getAmount();
        double utilization = getUtilization(addition.getTimeInBoil()) / 100;
        double alphaAcids = addition.getHop().getAlphaAcids().asFactor().value();
        double gravityAdjustment = getGravityAdjustment(boilGravity);

        double ibus = amount.value() * utilization * alphaAcids * 1000;
        ibus = ibus / (volume.value() * (1 + gravityAdjustment));

        return ibus;
    }

    private double getUtilization(Minutes timeInBoil) {
        return 18.11 + 13.86 * Math.tanh((timeInBoil.value() - 31.32) / 18.27);
    }

    private double getGravityAdjustment(SpecificGravity boilGravity) {
        double sg = boilGravity.value();
        return (sg > 1.050) ? (sg - 0.050) / 0.2 : 0;
    }

    public IBU getIBUsFromAddition(HopAddition addition, Liters volume, SpecificGravity boilGravity) {
        return new IBU(getRawIBUsFromAddition(addition, volume, boilGravity));
    }
}

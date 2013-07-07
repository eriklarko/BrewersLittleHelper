package blh.core.formulas.bitterness;

import blh.core.formulas.Formula;
import blh.core.recipe.HopAddition;
import blh.core.uncategorized.FullContext;
import blh.core.units.bitterness.IBU;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Grams;

/**
 * Taken from http://realbeer.com/hops/FAQ.html
 * <p/>
 * IBU = (GRAMS OF HOPS) * %UTILIZATION * %ALPHA * 1000 / VOLUME(litres) * (1 +
 * GA)
 * <p/>
 * %UTILIZATION = 18.11 + 13.86 * hyptan[(MINUTES - 31.32) / 18.27]
 * <p/>
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
        double utilization = getUtilization(addition.getTimeInBoil());
        double alphaAcids = addition.getHop().alphaAcids;

        double IBUs = (amount.value() * utilization * alphaAcids * 1000);
        IBUs = IBUs / (volume.value() * (1 + getGravityAdjustment(boilGravity)));

        return IBUs;
    }

    private double getUtilization(Minutes timeInBoil) {
        return 18.11 + 13.86 * Math.tanh((timeInBoil.value() - 31.32) / 18.27);
    }

    private double getGravityAdjustment(SpecificGravity boilGravity) {
        double sg = boilGravity.value();
        return (sg > 1.050) ? (sg - 1.050) / 0.2 : 0;
    }

    public IBU getIBUsFromAddition(HopAddition addition, Liters volume, SpecificGravity boilGravity) {
        return new IBU(getRawIBUsFromAddition(addition, volume, boilGravity));
    }
}

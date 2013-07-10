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
 * Taken from http://www.realbeer.com/hops/research.html
 *
 * @author thinner
 */
public class Tinseth implements Formula<IBU> {

    @Override
    public IBU calc(FullContext context) {
        double totalIBUs = 0;
        for (HopAddition addition : context.getIngredientsList().getHopAdditions()) {
            Liters boilVolume = context.getBoilVolumeAtMinutesLeft(addition.getTimeInBoil());
            SpecificGravity boilGravity = context.getBoilGravityAtMinutesLeft(addition.getTimeInBoil());
            totalIBUs += getRawIBUsForAddition(addition, boilVolume, boilGravity);
        }

        return new IBU(totalIBUs);
    }

    private double getRawIBUsForAddition(HopAddition addition, Liters boilVolume, SpecificGravity boilGravity) {
        double alpha = addition.getHop().alphaAcids;
        Grams mass = addition.getAmount();
        Minutes time = addition.getTimeInBoil();

        double milligramsPerLiter = (alpha * mass.value() * 1000) / boilVolume.value();
        double utilization = getUtilization(boilGravity, time);

        return milligramsPerLiter * utilization;
    }

    public double getUtilization(SpecificGravity boilGravity, Minutes timeInBoil) {
        double util = 1.65 * Math.pow(0.000125, boilGravity.value() - 1);
        util = util * (1 - Math.exp(-0.04 * timeInBoil.value()));
        util = util / 4.15;

        return util;
    }

    public IBU getIBUsForAddition(HopAddition addition, Liters boilVolume, SpecificGravity boilGravity) {
        return new IBU(getRawIBUsForAddition(addition, boilVolume, boilGravity));
    }
}

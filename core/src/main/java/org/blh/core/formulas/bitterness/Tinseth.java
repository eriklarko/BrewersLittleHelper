package org.blh.core.formulas.bitterness;

import org.blh.core.recipe.HopAddition;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Grams;

/**
 * Taken from http://www.realbeer.com/hops/research.html
 *
 * @author thinner
 */
public class Tinseth  {

    public IBU calc(Iterable<HopAddition> hopAdditions, Liters currentBoilVolume, SpecificGravity currentBoilGravity) {
        double totalIBUs = 0;
        for (HopAddition addition : hopAdditions) {
            totalIBUs += getRawIBUsForAddition(addition, currentBoilVolume, currentBoilGravity);
        }

        return new IBU(totalIBUs);
    }

    private double getRawIBUsForAddition(HopAddition addition, Liters boilVolume, SpecificGravity boilGravity) {
        double alpha = addition.getHop().getAlphaAcids().asFactor().inexactValue();
        Grams mass = addition.getAmount();
        Minutes time = addition.getTimeInBoil();

        double milligramsPerLiter = (alpha * mass.inexactValue() * 1000) / boilVolume.inexactValue();
        double utilization = getUtilization(boilGravity, time);

        return milligramsPerLiter * utilization;
    }

    private double getUtilization(SpecificGravity boilGravity, Minutes timeInBoil) {
        double util = 1.65 * Math.pow(0.000125, boilGravity.inexactValue() - 1);
        util = util * (1 - Math.exp(-0.04 * timeInBoil.inexactValue()));
        util = util / 4.15;

        return util;
    }

    public IBU getIBUsFromAddition(HopAddition addition, Liters boilVolume, SpecificGravity boilGravity) {
        return new IBU(getRawIBUsForAddition(addition, boilVolume, boilGravity));
    }
}

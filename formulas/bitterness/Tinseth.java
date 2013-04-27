package formulas.bitterness;

import formulas.Formula;
import recipe.HopAddition;
import uncategorized.FullContext;
import units.bitterness.IBU;
import units.gravity.SpecificGravity;
import units.time.Minutes;
import units.volume.Liters;
import units.weight.Grams;

/**
 * Taken from http://www.realbeer.com/hops/research.html
 * 
 * @author thinner
 */
public class Tinseth implements Formula<IBU> {

    @Override
    public IBU calc(FullContext context) {
        double totalIBUs = 0;
        //foreach hop addition in context
        HopAddition addition = null;
        Liters boilVolume = context.getBoilVolumeWithMinutesLeft(addition.getTimeInBoil());
        SpecificGravity boilGravity = context.getBoilGravityWithMinutesLeft(addition.getTimeInBoil());
        totalIBUs += getRawIBUsForAddition(null, boilVolume, boilGravity);

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

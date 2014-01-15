package org.blh.core.formulas.bitterness;

import org.blh.core.recipe.HopAddition;
import org.blh.core.units.Percentage;
import org.blh.core.units.bitterness.HBU;
import org.blh.core.units.weight.Oz;

/**
 * Taken from http://www.beertoolspro.com/wiki/Homebrew_Bitterness_Units
 *
 * @author thinner
 */
public class HBUFormula {

    public HBU calc(Iterable<HopAddition> hopAdditions) {
        double totalHBUs = 0;
        for(HopAddition addition : hopAdditions) {
            totalHBUs += getRawHBUs(new Oz(addition.getAmount()), addition.getHop().getAlphaAcids());
        }
        
        return new HBU(totalHBUs);
    }
    
    private double getRawHBUs(Oz amount, Percentage alphaAcids) {
        return amount.inexactValue() * alphaAcids.inexactValue();
    }
    
    public HBU getHBUsForAddition(HopAddition addition) {
        return new HBU(getRawHBUs(new Oz(addition.getAmount()), addition.getHop().getAlphaAcids()));
    }

}

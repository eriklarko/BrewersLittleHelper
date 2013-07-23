package org.blh.core.formulas.bitterness;

import org.blh.core.formulas.Formula;
import org.blh.core.recipe.HopAddition;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.Percentage;
import org.blh.core.units.bitterness.HBU;
import org.blh.core.units.weight.Oz;

/**
 * Taken from http://www.beertoolspro.com/wiki/Homebrew_Bitterness_Units
 *
 * @author thinner
 */
public class HBUFormula implements Formula<HBU>{

    @Override
    public HBU calc(FullContext context) {
        double totalHBUs = 0;
        for(HopAddition addition : context.getIngredientsList().getHopAdditions()) {
            totalHBUs += getRawHBUs(new Oz(addition.getAmount()), addition.getHop().alphaAcids);
        }
        
        return new HBU(totalHBUs);
    }
    
    private double getRawHBUs(Oz amount, Percentage alphaAcids) {
        return amount.value() * alphaAcids.value();
    }
    
    public HBU getHBUsForAddition(HopAddition addition) {
        return new HBU(getRawHBUs(new Oz(addition.getAmount()), addition.getHop().alphaAcids));
    }

}

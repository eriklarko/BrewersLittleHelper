package blh.core.formulas.bitterness;

import blh.core.formulas.Formula;
import blh.core.recipe.HopAddition;
import blh.core.uncategorized.FullContext;
import blh.core.units.Percentage;
import blh.core.units.bitterness.HBU;
import blh.core.units.weight.Oz;

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

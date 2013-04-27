package blh.core.formulas.bitterness;

import blh.core.formulas.Formula;
import blh.core.recipe.HopAddition;
import blh.core.uncategorized.FullContext;
import blh.core.units.bitterness.HBU;

/**
 *
 * @author thinner
 */
public class HBUFormula implements Formula<HBU>{

    @Override
    public HBU calc(FullContext context) {
        // TODO: FIX LOOP!
        double totalHBUs = 0;
        // for each hop addition
            totalHBUs += getRawHBUs(null);
        
        return new HBU(totalHBUs);
    }
    
    private double getRawHBUs(HopAddition addition) {
        return addition.getAmount().value() * addition.getHop().alphaAcids;
    }
    
    public HBU getHBUsForAddition(HopAddition addition) {
        return new HBU(getRawHBUs(addition));
    }

}

package formulas.bitterness;

import formulas.Formula;
import recipe.HopAddition;
import uncategorized.FullContext;
import units.bitterness.HBU;

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

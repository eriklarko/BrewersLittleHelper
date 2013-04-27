package formulas.alcohol;

import formulas.Formula;
import uncategorized.FullContext;
import units.alcohol.ABV;
import units.gravity.SpecificGravity;

/**
 * Taken from http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
 *
 * ABV = (og – fg) * 131.25;
 * 
 * @author thinner
 */
public class BrewersFriendSimple implements Formula<ABV> {

    @Override
    public ABV calc(FullContext context) {
        return calc(context.getOriginalGravity(), context.getFinalGravity());
    }
    
    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        double og = originalGravity.value();
        double fg = finalGravity.value();
        
        return new ABV((og - fg) * 131.25);
    }
}

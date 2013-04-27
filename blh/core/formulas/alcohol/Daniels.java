package blh.core.formulas.alcohol;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.alcohol.ABV;
import blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
 * 
 * ABV = (76.08 * (og-fg) / (1.775-og)) * (fg / 0.794)
 *
 * @author thinner
 */
public class Daniels implements Formula<ABV> {

    @Override
    public ABV calc(FullContext context) {
        return calc(context.getOriginalGravity(), context.getFinalGravity());
    }
    
    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        double og = originalGravity.value();
        double fg = finalGravity.value();
        
        return new ABV((76.08 * (og-fg) / (1.775-og)) * (fg / 0.794));
    }

}

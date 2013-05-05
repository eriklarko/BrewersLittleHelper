package blh.core.formulas.alcohol;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.alcohol.ABV;
import blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://byo.com/component/k2/item/1658-write-your-own-brewing-spreadsheet?Itemid=398
 * 
 * ABV = (OG - FG) * 0.129
 *   where OG and FG are in gravity points (GP), not specific gravity (SG)
 * ABV = (OG / 1000 - FG / 1000) * 0.129
 *   Since SpecificGravity objects always are on the form GP/1000 we get
 * ABV = (OG' - FG') * 0.129
 * 
 * STRANGENESS!
 *   1.040 - 1.010 = 1.030, multiplying this with a number less than zero
 *   will make it even lower, and the ABV for a beer with OG=1.040 and 
 *   FG=1.030 will not be lower than 1.010, it should be around 4.
 *   Conculsion; the 0.129 probably scaled because the formula is based on GPs,
 *     and it should then be unscaled to be correct for SGs giving it the value
 *     0.129 * 1000 = 129.
 * 
 * @author thinner
 */
public class BYOSimple implements Formula<ABV>{

    @Override
    public ABV calc(FullContext context) {
        return calc(context.originalGravity.value(), context.finalGravity.value());
    }
    
    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        double og = originalGravity.value();
        double fg = finalGravity.value();
        
        return new ABV((og - fg) * 129);
    }

}

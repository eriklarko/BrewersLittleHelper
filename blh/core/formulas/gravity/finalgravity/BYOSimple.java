package blh.core.formulas.gravity.finalgravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://byo.com/component/k2/item/1658-write-your-own-brewing-spreadsheet?Itemid=398
 * 
 * FG = OG (1 - AA)
 *   where AA is the yeasts' apparent attenuation
 * 
 * @author thinner
 */
public class BYOSimple implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        double og = context.getOriginalGravity().value();
        
        return calc(og, context.getYeastApparentAttenuation());
    }

    public SpecificGravity calc(double og, double yeastApparentAttenuation) {
        return new SpecificGravity(og * (1 - yeastApparentAttenuation));
    }
    
}

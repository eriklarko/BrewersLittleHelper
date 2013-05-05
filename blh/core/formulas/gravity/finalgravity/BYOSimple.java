package blh.core.formulas.gravity.finalgravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.Factor;
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
        double og = context.originalGravity.value().value();
        
        return calc(og, context.yeastApparentAttenuation.value());
    }

    public SpecificGravity calc(double og, Factor yeastApparentAttenuation) {
        return new SpecificGravity(og * (1 - yeastApparentAttenuation.value()));
    }
    
}

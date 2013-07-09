package blh.core.formulas.gravity.finalgravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.Factor;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://byo.com/component/k2/item/1658-write-your-own-brewing-spreadsheet?Itemid=398
 *
 * FG = OG (1 - AA)
 *   where AA is the yeasts' apparent attenuation
 *   OG and FG are in GP
 * 
 * @author thinner
 */
public class BYOSimple implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        return calc(context.originalGravity.value(), context.yeastApparentAttenuation.value());
    }
    
    public SpecificGravity calc(SpecificGravity og, Factor yeastApparentAttenuation) {
        return calc(new GravityPoints(og), yeastApparentAttenuation);
    }

    public SpecificGravity calc(GravityPoints og, Factor yeastApparentAttenuation) {
        return new GravityPoints(og.value() * (1 - yeastApparentAttenuation.value())).toSpecificGravity();
    }
    
}

package org.blh.core.formulas.gravity.finalgravity;

import org.blh.core.units.Factor;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://byo.com/component/k2/item/1658-write-your-own-brewing-spreadsheet?Itemid=398
 *
 * FG = OG (1 - AA)
 *   where AA is the yeasts' apparent attenuation
 *   OG and FG are in GP
 * 
 * @author thinner
 */
public class BYOSimple  {

    public SpecificGravity calc(SpecificGravity og, Factor yeastApparentAttenuation) {
        return calc(new GravityPoints(og), yeastApparentAttenuation);
    }

    public SpecificGravity calc(GravityPoints og, Factor yeastApparentAttenuation) {
        return new GravityPoints(og.inexactValue() * (1 - yeastApparentAttenuation.inexactValue())).toSpecificGravity();
    }
    
}

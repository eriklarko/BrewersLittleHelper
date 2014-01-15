package org.blh.core.formulas.gravity.finalgravity;

import org.blh.core.units.Factor;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://brewgr.com/calculations/final-gravity
 * Final Gravity = 1 + ((Total Gravity Points * (1 - Attenuation factor)) / 1000)
 *
 * Created by Erik Larkö at 7/2/13 11:10 PM
 */
public class Brewgr  {

    public SpecificGravity calc(SpecificGravity og, Factor yeastAttenuation) {
        return calc(new GravityPoints(og), yeastAttenuation);
    }
    
    public SpecificGravity calc(GravityPoints og, Factor yeastAttenuation) {
        return new SpecificGravity(1 + (og.inexactValue() * (1 - yeastAttenuation.inexactValue()) ) / 1000);
    }
}

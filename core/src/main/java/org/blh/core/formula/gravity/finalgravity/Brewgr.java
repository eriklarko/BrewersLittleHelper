package org.blh.core.formula.gravity.finalgravity;

import org.blh.core.unit.Factor;
import org.blh.core.unit.gravity.GravityPoints;
import org.blh.core.unit.gravity.SpecificGravity;

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
        return new SpecificGravity(1 + (og.value() * (1 - yeastAttenuation.value()) ) / 1000);
    }
}

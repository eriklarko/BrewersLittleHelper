package blh.core.formulas.gravity.finalgravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.Factor;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://brewgr.com/calculations/final-gravity
 * Final Gravity = 1 + ((Total Gravity Points * (1 - Attenuation factor)) / 1000)
 *
 * Created by Erik Larkö at 7/2/13 11:10 PM
 */
public class Brewgr implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        GravityPoints og = new GravityPoints(context.originalGravity.value());
        Factor yeastAttenuation = context.yeastApparentAttenuation.value();

        return calc(og, yeastAttenuation);
    }

    public SpecificGravity calc(GravityPoints og, Factor yeastAttenuation) {
        return new SpecificGravity(1 + (og.value() * (1 - yeastAttenuation.value()) ) / 1000);
    }
}

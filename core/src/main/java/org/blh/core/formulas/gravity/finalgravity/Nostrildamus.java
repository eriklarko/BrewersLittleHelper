package org.blh.core.formulas.gravity.finalgravity;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.Factor;
import org.blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://www.homebrewtalk.com/f13/estimate-final-gravity-32826/#post322609
 * 1.055 = 55 GU * 0.25 = 13.75 or 1.01375
 * FG = (OG - 1)*1000 * (1 - AA) / 1000
 *    = (OG - 1) * (1 - AA) + 1
 *
 * Created by Erik Larkö at 7/2/13 10:58 PM
 */
public class Nostrildamus implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        return calc(context.originalGravity.value(), context.yeastApparentAttenuation.value());
    }

    public SpecificGravity calc(SpecificGravity og, Factor yeastAttenuation) {
        double fg = (og.inexactValue() - 1) * (1 - yeastAttenuation.inexactValue()) + 1;
        return new SpecificGravity(fg);
    }
}

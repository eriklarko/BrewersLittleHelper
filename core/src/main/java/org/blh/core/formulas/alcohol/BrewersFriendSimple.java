package org.blh.core.formulas.alcohol;

import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.gravity.SpecificGravity;


/**
 * Taken from http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
 *
 * ABV = (og – fg) * 131.25;
 *
 * @author thinner
 */
public class BrewersFriendSimple  {

    public static final double SCALING_FACTOR = 131.25;

    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        double ogFgDiff = originalGravity.value() - finalGravity.value();

        return new ABV(ogFgDiff * SCALING_FACTOR);
    }
}

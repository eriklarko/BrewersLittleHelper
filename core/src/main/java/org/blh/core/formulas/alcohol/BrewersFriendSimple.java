package org.blh.core.formulas.alcohol;

import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.gravity.SpecificGravity;

import java.math.BigDecimal;

/**
 * Taken from http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
 *
 * ABV = (og – fg) * 131.25;
 *
 * @author thinner
 */
public class BrewersFriendSimple  {

    public static final BigDecimal SCALING_FACTOR = BigDecimal.valueOf(131.25);

    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        BigDecimal ogFgDiff = originalGravity.value().subtract(finalGravity.value());

        return new ABV(ogFgDiff.multiply(SCALING_FACTOR));
    }
}

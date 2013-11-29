package org.blh.core.formulas.alcohol;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
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
public class BrewersFriendSimple implements Formula<ABV> {

    public static final BigDecimal SCALING_FACTOR = BigDecimal.valueOf(131.25);

    @Override
    public ABV calc(FullContext context) {
        return calc(context.originalGravity.value(), context.finalGravity.value());
    }

    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        BigDecimal ogFgDiff = originalGravity.value().subtract(finalGravity.value());

        return new ABV(ogFgDiff.multiply(SCALING_FACTOR));
    }
}

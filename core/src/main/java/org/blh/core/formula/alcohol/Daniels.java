package org.blh.core.formula.alcohol;

import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.gravity.SpecificGravity;

/**
 * Taken from
 * http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
 *
 * ABV = (76.08 * (og-fg) / (1.775-og)) * (fg / 0.794)
 *
 * @author thinner
 */
public class Daniels {

    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        double og = originalGravity.value();
        double fg = finalGravity.value();

        return new ABV((76.08 * (og - fg) / (1.775 - og)) * (fg / 0.794));
    }
}

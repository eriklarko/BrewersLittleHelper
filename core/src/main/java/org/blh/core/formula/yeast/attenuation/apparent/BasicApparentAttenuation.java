package org.blh.core.formula.yeast.attenuation.apparent;

import org.blh.core.unit.Factor;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.gravity.SpecificGravity;

/**
 * http://www.whitelabs.com/beer/homebrew/beginners-attenuation-and-flocculation-definitions
 * Attenuation = (OG-FG)/(OG-1)
 *
 * http://www.howtobrew.com/section1/chapter6-1.html % att. = (OG-FG)/OG)
 *
 * http://pint.com.au/calculators/alcohol/ Apparent Attenuation % =
 * ((OG-1)-(FG-1)) / (OG-1) x 100% Apparent Attenuation % = (OG-FG)/ OG x 100%
 *
 *
 * @author eriklark
 */
public class BasicApparentAttenuation {

    public Percentage calc(SpecificGravity og, SpecificGravity fg) {
        return new Factor((og.value() - fg.value()) / (og.value() - 1)).asPercentage();
    }
}

package org.blh.core.formula.yeast.attenuation.actual;

import org.blh.core.unit.Percentage;

/**
 * http://pint.com.au/calculators/alcohol/
 *
 * Actual Attenuation % = Apparent Attenuation % x 0.814
 * @author eriklark
 */
public class PintAttenuation {

    public Percentage calc(Percentage apparentAttenuation) {
        return new Percentage(apparentAttenuation.value() * 0.814);
    }
}

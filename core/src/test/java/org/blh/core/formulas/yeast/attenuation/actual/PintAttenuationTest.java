package org.blh.core.formulas.yeast.attenuation.actual;

import org.blh.core.formula.yeast.attenuation.actual.PintAttenuation;
import org.blh.core.unit.Percentage;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author eriklark
 */
public class PintAttenuationTest {

    @Test
    /**
     * Values taken from http://pint.com.au/calculators/alcohol/
     * OG: 1.059
     * FG: 1.010
     */
    public void test() {
        PintAttenuation f = new PintAttenuation();
        Percentage apparentAttenuation = new Percentage(83.05);

        Percentage expected = new Percentage(67.6);
        expected.setDelta(1);
        Percentage actual = f.calc(apparentAttenuation);
        Assert.assertEquals(expected, actual);
    }
}

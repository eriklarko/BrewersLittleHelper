package org.blh.core.formulas.yeast.attenuation.apparent;

import org.blh.core.formula.yeast.attenuation.apparent.BasicApparentAttenuation;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.gravity.SpecificGravity;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author eriklark
 */
public class BasicApparentAttenuationTest {

    @Test
    /**
     * Values taken from http://pint.com.au/calculators/alcohol/
     * OG: 1.059
     * FG: 1.010
     */
    public void test() {
        BasicApparentAttenuation f = new BasicApparentAttenuation();

        Percentage expected = new Percentage(83.05);
        expected.setDelta(2);
        Percentage actual = f.calc(new SpecificGravity(1.059), new SpecificGravity(1.010));

        Assert.assertEquals(expected, actual);
    }
}

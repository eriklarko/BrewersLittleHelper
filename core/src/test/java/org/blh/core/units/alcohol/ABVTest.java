package org.blh.core.units.alcohol;

import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.Factor;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class ABVTest {

    @Test
    public void testABVFactor() {
        ABV actual = new ABV(new Factor(1));
        Factor expected = new Factor(1);

        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testABVDouble() {
        ABV actual = new ABV(1);
        double expected = 1;

        Assert.assertEquals(expected, actual.value().value(), 0.0001);
    }
}

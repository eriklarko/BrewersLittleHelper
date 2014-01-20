package org.blh.core.units.quantity;

import org.blh.core.unit.quantity.Million;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class MillionTest {

    @Test
    public void testDouble() {
        Million actual = new Million(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testTrueValue() {
        Million actual = new Million(2);
        double expected = 2_000_000;

        Assert.assertEquals(expected, actual.trueValue(), 0.0001);
    }
}

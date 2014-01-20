package org.blh.core.units.quantity;

import org.blh.core.unit.quantity.Billion;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class BillionTest {

    @Test
    public void testDouble() {
        Billion actual = new Billion(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testTrueValue() {
        Billion actual = new Billion(2);
        double expected = 2_000_000_000;

        Assert.assertEquals(expected, actual.trueValue(), 0.0001);
    }
}

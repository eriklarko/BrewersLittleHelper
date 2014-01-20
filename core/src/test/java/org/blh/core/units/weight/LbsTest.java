package org.blh.core.units.weight;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class LbsTest {

    @Test
    public void testDouble() {
        Lbs actual = new Lbs(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testKilograms() {
        Lbs actual = new Lbs(new Kilograms(2));
        Lbs expected = new Lbs(4.40924524);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToKilograms() {
        Kilograms actual = new Lbs(3).toKilograms();
        Kilograms expected = new Kilograms(1.36077711);

        Assert.assertEquals(expected, actual);
    }
}

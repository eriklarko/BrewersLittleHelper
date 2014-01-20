package org.blh.core.units.weight;

import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class GramsTest {

    @Test
    public void testDouble() {
        Grams actual = new Grams(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testKilograms() {
        Grams actual = new Grams(new Kilograms(2));
        Grams expected = new Grams(2000);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToKilograms() {
        Kilograms actual = new Grams(3000).toKilograms();
        Kilograms expected = new Kilograms(3);

        Assert.assertEquals(expected, actual);
    }
}

package org.blh.core.units.weight;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class OzTest {

    @Test
    public void testDouble() {
        Oz actual = new Oz(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testGrams() {
        Oz actual = new Oz(new Grams(2));
        Oz expected = new Oz(0.0705479239);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToGrams() {
        Grams actual = new Oz(3).toGrams();
        Grams expected = new Grams(85.0485694);

        Assert.assertEquals(expected, actual);
    }
}

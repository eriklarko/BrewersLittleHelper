package org.blh.core.units.quantity;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class YeastCellCountTest {

    @Test
    public void testInt() {
        YeastCellCount actual = new YeastCellCount(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value().value(), 0.0001);
    }

    @Test
    public void testMillion() {
        YeastCellCount actual = new YeastCellCount(new Million(2));
        Billion expected = new Billion(0.002);

        Assert.assertEquals(expected.value(), actual.value().value());
    }

    @Test
    public void testBillion() {
        YeastCellCount actual = new YeastCellCount(new Billion(2));
        Billion expected = new Billion(2);

        Assert.assertEquals(expected.value(), actual.value().value());
    }
}

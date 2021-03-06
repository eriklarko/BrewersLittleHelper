package org.blh.core.units.distance;

import org.blh.core.unit.distance.Feet;
import org.blh.core.unit.distance.Meters;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class FeetTest {

    @Test
    public void testDouble() {
        Feet actual = new Feet(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testMeters() {
        Feet actual = new Feet(new Meters(2));
        Feet expected = new Feet(6.56167979);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToMeters() {
        Meters actual = new Feet(2).toMeters();
        Meters expected = new Meters(0.6096);

        Assert.assertEquals(expected, actual);
    }
}

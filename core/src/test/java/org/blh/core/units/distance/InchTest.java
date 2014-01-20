package org.blh.core.units.distance;

import org.blh.core.unit.distance.Inch;
import org.blh.core.unit.distance.Meters;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class InchTest {

    @Test
    public void testDouble() {
        Inch actual = new Inch(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testMeters() {
        Inch actual = new Inch(new Meters(2));
        Inch expected = new Inch(78.7401575);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToMeters() {
        Meters actual = new Inch(2).toMeters();
        Meters expected = new Meters(0.0508);

        Assert.assertEquals(expected, actual);
    }
}

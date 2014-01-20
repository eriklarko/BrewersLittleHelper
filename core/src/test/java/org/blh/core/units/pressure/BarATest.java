package org.blh.core.units.pressure;

import org.blh.core.unit.pressure.Bar;
import org.blh.core.unit.pressure.BarA;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class BarATest {

    @Test
    public void testDouble() {
        BarA actual = new BarA(2);
        double expected = 2;

        Assert.assertEquals(expected, actual.value(), 0.0001);
    }

    @Test
    public void testBar() {
        BarA actual = new BarA(new Bar(1));
        BarA expected = new BarA(2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToBar() {
        Bar actual = new BarA(3).toBar();
        Bar expected = new Bar(2);

        Assert.assertEquals(expected, actual);
    }
}

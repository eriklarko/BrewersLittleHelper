package test.blh.core.units.distance;

import blh.core.units.distance.Feet;
import blh.core.units.distance.Meters;
import junit.framework.Assert;
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
        
        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testMeters() {
        Feet actual = new Feet(new Meters(2));
        Feet expected = new Feet(6.56167979);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testToMeters() {
        Meters actual = new Feet(2).toMeters();
        Meters expected = new Meters(0.6096);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }
}

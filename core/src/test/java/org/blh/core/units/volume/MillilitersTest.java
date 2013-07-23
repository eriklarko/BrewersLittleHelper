package org.blh.core.units.volume;

import org.blh.core.units.volume.Milliliters;
import org.blh.core.units.volume.Liters;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class MillilitersTest {
    
    @Test
    public void testDouble() {
        Milliliters actual = new Milliliters(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value(), 0);
    }
    
    @Test
    public void testLiters() {
        Milliliters actual = new Milliliters(new Liters(2));
        Milliliters expected = new Milliliters(2000);
        
        Assert.assertEquals(expected.value(), actual.value(), 0);
    }

    @Test
    public void testToLiters() {
        Liters actual = new Milliliters(3000).toLiters();
        Liters expected = new Liters(3);
        
        Assert.assertEquals(expected.value(), actual.value(), 0);
    }
}
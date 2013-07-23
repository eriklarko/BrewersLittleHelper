package org.blh.core.units.pressure;

import org.blh.core.units.pressure.Bar;
import org.blh.core.units.pressure.PSI;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class PSITest {
    
    @Test
    public void testDouble() {
        PSI actual = new PSI(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value(), 0);
    }

    @Test
    public void testBar() {
        PSI actual = new PSI(new Bar(1));
        PSI expected = new PSI(14.5037738);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testToBar() {
        Bar actual = new PSI(1).toBar();
        Bar expected = new Bar(0.0689475729);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }
}

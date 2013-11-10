package org.blh.core.units.pressure;

import java.math.BigDecimal;
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
        BigDecimal expected = new BigDecimal(2);
        
        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testBar() {
        PSI actual = new PSI(new Bar(1));
        PSI expected = new PSI(14.5037738);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testToBar() {
        Bar actual = new PSI(1).toBar();
        Bar expected = new Bar(0.0689475729);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}

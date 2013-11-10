package org.blh.core.units.volume;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class USGallonsTest {
    
    @Test
    public void testDouble() {
        USGallons actual = new USGallons(2);
        BigDecimal expected = new BigDecimal(2);
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testLiters() {
        USGallons actual = new USGallons(new Liters(2));
        USGallons expected = new USGallons(0.528344105);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testToLiters() {
        Liters actual = new USGallons(5).toLiters();
        Liters expected = new Liters(18.9270589);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}

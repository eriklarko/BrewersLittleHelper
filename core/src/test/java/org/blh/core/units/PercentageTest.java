package org.blh.core.units;

import org.blh.core.units.Percentage;
import org.blh.core.units.Factor;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class PercentageTest {
    
        @Test
    public void testDouble() {
        Percentage actual = new Percentage(2);
        double ePercentagepected = 2;
        
        Assert.assertEquals(ePercentagepected, actual.value());
    }
    
    @Test
    public void testFactor() {
        Percentage actual = new Percentage(new Factor(0.2));
        Percentage ePercentagepected = new Percentage(20);
        
        Assert.assertEquals(ePercentagepected.value(), actual.value());
    }

    @Test
    public void testAsFactor() {
        Factor actual = new Percentage(1).asFactor();
        Factor ePercentagepected = new Factor(0.01);
        
        Assert.assertEquals(ePercentagepected.value(), actual.value());
    }
}

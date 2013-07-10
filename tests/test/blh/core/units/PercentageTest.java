/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blh.core.units;

import blh.core.units.Percentage;
import blh.core.units.Factor;
import junit.framework.Assert;
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
        
        Assert.assertEquals(ePercentagepected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testAsFactor() {
        Factor actual = new Percentage(1).asFactor();
        Factor ePercentagepected = new Factor(0.01);
        
        Assert.assertEquals(ePercentagepected.value(), actual.value(), 0.00001);
    }
}

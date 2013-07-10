/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blh.core.units;

import blh.core.units.Factor;
import blh.core.units.Percentage;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class FactorTest {
    
        @Test
    public void testDouble() {
        Factor actual = new Factor(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testPercentage() {
        Factor actual = new Factor(new Percentage(2));
        Factor expected = new Factor(0.02);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testAsPercentage() {
        Percentage actual = new Factor(0.1).asPercentage();
        Percentage expected = new Percentage(10);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }
}

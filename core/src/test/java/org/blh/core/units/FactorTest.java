package org.blh.core.units;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class FactorTest {
    
    @Test
    public void testDouble() {
        Factor actual = new Factor(2);
        BigDecimal expected = new BigDecimal(2);
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testPercentage() {
        Factor actual = new Factor(new Percentage(2));
        Factor expected = new Factor(0.02);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testAsPercentage() {
        Percentage actual = new Factor(0.1).asPercentage();
        Percentage expected = new Percentage(10);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}

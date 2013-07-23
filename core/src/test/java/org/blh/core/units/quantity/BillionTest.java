package org.blh.core.units.quantity;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class BillionTest {
    
    @Test
    public void testDouble() {
        Billion actual = new Billion(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value(), 0);
    }

    @Test
    public void testTrueValue() {
        Billion actual = new Billion(2);
        BigDecimal expected = new BigDecimal(2_000_000_000d);
        
        Assert.assertEquals(expected, actual.trueValue());
    }
}

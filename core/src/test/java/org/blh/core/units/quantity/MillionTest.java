package org.blh.core.units.quantity;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class MillionTest {
    
    @Test
    public void testDouble() {
        Million actual = new Million(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value(), 0);
    }

    @Test
    public void testTrueValue() {
        Million actual = new Million(2);
        BigDecimal expected = new BigDecimal(2_000_000);
        
        Assert.assertEquals(expected, actual.trueValue());
    }
}

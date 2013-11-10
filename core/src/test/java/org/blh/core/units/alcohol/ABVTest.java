package org.blh.core.units.alcohol;

import java.math.BigDecimal;
import org.blh.core.units.Factor;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class ABVTest {
    
    @Test
    public void testABVFactor() {
        ABV actual = new ABV(new Factor(1));
        Factor expected = new Factor(1);
        
        Assert.assertEquals(expected.value(), actual.value().value());
    }

    @Test
    public void testABVDouble() {
        ABV actual = new ABV(1);
        BigDecimal expected = new BigDecimal(1);
        
        Assert.assertEquals(expected, actual.value().value());
    }
}

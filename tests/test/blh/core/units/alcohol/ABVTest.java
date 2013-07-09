package test.blh.core.units.alcohol;

import blh.core.units.Factor;
import blh.core.units.alcohol.ABV;
import junit.framework.Assert;
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
        double expected = 1;
        
        Assert.assertEquals(expected, actual.value().value());
    }
}

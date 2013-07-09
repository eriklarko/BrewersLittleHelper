package test.blh.core.units.quantity;

import blh.core.units.quantity.Billion;
import java.math.BigDecimal;
import junit.framework.Assert;
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
        
        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testTrueValue() {
        Billion actual = new Billion(2);
        BigDecimal expected = new BigDecimal(2_000_000_000d);
        
        Assert.assertEquals(expected, actual.trueValue());
    }
}

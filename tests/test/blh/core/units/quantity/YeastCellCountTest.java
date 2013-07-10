package test.blh.core.units.quantity;

import blh.core.units.quantity.Billion;
import blh.core.units.quantity.Million;
import blh.core.units.quantity.YeastCellCount;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class YeastCellCountTest {
    
    @Test
    public void testInt() {
        YeastCellCount actual = new YeastCellCount(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value().value());
    }
    
    @Test
    public void testMillion() {
        YeastCellCount actual = new YeastCellCount(new Million(2));
        Billion expected = new Billion(0.002);
        
        Assert.assertEquals(expected.value(), actual.value().value());
    }
    
    @Test
    public void testBillion() {
        YeastCellCount actual = new YeastCellCount(new Billion(2));
        Billion expected = new Billion(2);
        
        Assert.assertEquals(expected.value(), actual.value().value());
    }
}

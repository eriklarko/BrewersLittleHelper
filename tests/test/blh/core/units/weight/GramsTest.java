package test.blh.core.units.weight;

import blh.core.units.weight.Grams;
import blh.core.units.weight.Kilograms;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class GramsTest {
    
    @Test
    public void testDouble() {
        Grams actual = new Grams(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testKilograms() {
        Grams actual = new Grams(new Kilograms(2));
        Grams expected = new Grams(2000);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testToKilograms() {
        Kilograms actual = new Grams(3000).toKilograms();
        Kilograms expected = new Kilograms(3);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}

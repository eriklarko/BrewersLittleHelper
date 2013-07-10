package test.blh.core.units.weight;

import blh.core.units.weight.Oz;
import blh.core.units.weight.Grams;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class OzTest {
    
    @Test
    public void testDouble() {
        Oz actual = new Oz(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testGrams() {
        Oz actual = new Oz(new Grams(2));
        Oz expected = new Oz(0.0705479239);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testToGrams() {
        Grams actual = new Oz(3).toGrams();
        Grams expected = new Grams(85.0485694);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }
}

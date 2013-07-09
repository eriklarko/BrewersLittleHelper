package test.blh.core.units.gravity;

import blh.core.units.gravity.Plato;
import blh.core.units.gravity.SpecificGravity;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class PlatoTest {
    
    @Test
    public void testDouble() {
        Plato actual = new Plato(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testSpecificGravity() {
        Plato actual = new Plato(new SpecificGravity(1.020));
        Plato expected = new Plato(5);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testToSpecificGravity() {
        SpecificGravity actual = new Plato(3).toSpecificGravity();
        SpecificGravity expected = new SpecificGravity(1.012);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.001);
    }
}

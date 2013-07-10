package test.blh.core.units;

import blh.core.units.ExtractPotential;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.weight.Kilograms;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class ExtractPotentialTest {
    
    @Test
    public void testConstructor() {
        GravityPoints gp = new GravityPoints(2);
        Kilograms weight = new Kilograms(3);
        
        ExtractPotential actual = new ExtractPotential(gp, weight);
        double expectedValue = 2/3d;
        
        Assert.assertEquals("Value", expectedValue, actual.value());
        Assert.assertEquals("Gravity points", gp.value(), actual.getGravityPoints().value());
        Assert.assertEquals("Weight", weight.value(), actual.getWeight().value());
    }
}

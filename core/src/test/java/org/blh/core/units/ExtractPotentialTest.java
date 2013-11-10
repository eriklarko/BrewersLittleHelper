package org.blh.core.units;

import java.math.BigDecimal;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.weight.Kilograms;
import org.junit.Assert;
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
        BigDecimal expectedValue = new BigDecimal(2).divide(new BigDecimal(3));
        
        Assert.assertEquals("Value", expectedValue, actual.value());
        Assert.assertEquals("Gravity points", gp.value(), actual.getGravityPoints().value());
        Assert.assertEquals("Weight", weight.value(), actual.getWeight().value());
    }
}

package org.blh.core.units;

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
        double expectedValue = 2d / 3;

        Assert.assertEquals("Value", expectedValue, actual.value(), 0.0001);
        Assert.assertEquals("Gravity points", gp, actual.getGravityPoints());
        Assert.assertEquals("Weight", weight, actual.getWeight());
    }
}

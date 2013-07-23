package org.blh.core.units.color;

import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.color.Lovibond;
import org.blh.core.units.weight.Lbs;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class ColorPotentialTest {
    
    @Test
    public void testConstructor() {
        ColorPotential actual = new ColorPotential();
        
        Assert.assertEquals(0d, actual.value(), 0);
    }

    @Test
    public void testAdd() {
        Lovibond color = new Lovibond(3);
        Lbs amount = new Lbs(5);
        
        ColorPotential actual = new ColorPotential();
        
        actual.add(color, amount);
        Assert.assertEquals("First add", 15d, actual.value(), 0);
        
        actual.add(color, amount);
        Assert.assertEquals("Second add", 30d, actual.value(), 0);
        
        actual.add(color, amount);
        Assert.assertEquals("Third add", 45d, actual.value(), 0);
    }
}

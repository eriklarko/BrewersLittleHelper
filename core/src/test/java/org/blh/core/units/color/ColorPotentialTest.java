package org.blh.core.units.color;

import java.math.BigDecimal;
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
        
        Assert.assertEquals(BigDecimal.ZERO, actual.value());
    }

    @Test
    public void testAdd() {
        Lovibond color = new Lovibond(3);
        Lbs amount = new Lbs(5);
        
        ColorPotential actual = new ColorPotential();
        
        actual.add(color, amount);
        Assert.assertEquals("First add", new BigDecimal(15d), actual.value());
        
        actual.add(color, amount);
        Assert.assertEquals("Second add", new BigDecimal(30d), actual.value());
        
        actual.add(color, amount);
        Assert.assertEquals("Third add", new BigDecimal(45d), actual.value());
    }
}

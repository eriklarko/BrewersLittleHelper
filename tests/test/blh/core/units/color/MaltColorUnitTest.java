package test.blh.core.units.color;

import blh.core.units.color.ColorPotential;
import blh.core.units.color.Lovibond;
import blh.core.units.color.MaltColorUnit;
import blh.core.units.volume.USGallons;
import blh.core.units.weight.Lbs;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class MaltColorUnitTest {
    
    @Test
    public void testSomeMethod() {
        ColorPotential potential = new ColorPotential(new Lovibond(3), new Lbs(1));
        USGallons finalVolume = new USGallons(5);
        
        MaltColorUnit actual = new MaltColorUnit(potential, finalVolume);
        double expected = 3/5d;
        
        Assert.assertEquals(expected, actual.value());
    }
}

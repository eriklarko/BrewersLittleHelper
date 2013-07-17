package test.blh.core.formulas.volumes.water;

import blh.core.formulas.volumes.water.BrewStep;
import blh.core.formulas.volumes.water.VolumeCalculator;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class VolumeCalculatorTest {
    
    public VolumeCalculatorTest() {
    }

    @Test
    public void testPre() {
        System.out.println("pre");
        BrewStep target = null;
        FullContext context = null;
        VolumeCalculator instance = null;
        Liters expResult = null;
        Liters result = instance.pre(target, context);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPost() {
        System.out.println("post");
        BrewStep target = null;
        FullContext context = null;
        VolumeCalculator instance = null;
        Liters expResult = null;
        Liters result = instance.post(target, context);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

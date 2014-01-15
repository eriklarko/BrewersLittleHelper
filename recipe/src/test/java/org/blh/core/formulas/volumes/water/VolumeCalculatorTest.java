package org.blh.core.formulas.volumes.water;

import org.blh.recipe.volumes.water.BrewStep;
import org.blh.recipe.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;
import java.util.Arrays;
import java.util.LinkedList;
import org.blh.recipe.volumes.water.VolumeCalculator;
import org.blh.recipe.volumes.water.impl.BoilStep;
import org.blh.recipe.volumes.water.impl.CoolingStep;
import org.blh.recipe.volumes.water.impl.Fermentation;
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
    public void testPreFermentationKnowingBoilVolume() {
        FullContext context = null;
        BrewStep target = new Fermentation();
        VolumeCalculator instance = new VolumeCalculator(new LinkedList<>(Arrays.asList(
            new BoilStep(new Liters(10)), new CoolingStep(), target
        )));

        Liters expResult = new Liters(10*0.9);
        Liters result = instance.pre(target, context);
        assertEquals(expResult, result);
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

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
import org.junit.Ignore;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
@Ignore
public class VolumeCalculatorTest {

    public VolumeCalculatorTest() {
    }

    @Test
    public void testPreFermentationKnowingBoilVolume() {
        FullContext context = new FullContext();
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
        fail("The test case is a prototype.");
    }

}

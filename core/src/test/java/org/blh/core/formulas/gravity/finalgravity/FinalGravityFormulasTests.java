package org.blh.core.formulas.gravity.finalgravity;

import org.blh.core.units.Factor;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.gravity.SpecificGravity;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 7/2/13 10:18 PM
 */
public class FinalGravityFormulasTests {

    private final SpecificGravity og = new SpecificGravity(1.055);
    private final Factor yeastAttenuation = new Factor(0.75);

    @Test
    public void testBYOSimple() {
        SpecificGravity expectedFG = new SpecificGravity(1.01375);

        BYOSimple f = new BYOSimple();
        SpecificGravity actualFG1 = f.calc(new GravityPoints(og), yeastAttenuation);
        SpecificGravity actualFG2 = f.calc(og, yeastAttenuation);

        Assert.assertEquals("Original failed", expectedFG.value(), actualFG1.value());
        Assert.assertEquals("Tweaked failed", expectedFG.value(), actualFG2.value());
    }

    @Test
    public void testNostrilDamus() {
        SpecificGravity expectedFG = new SpecificGravity(1.01375);

        Nostrildamus f = new Nostrildamus();
        SpecificGravity actualFG = f.calc(og, yeastAttenuation);

        Assert.assertEquals(expectedFG.value(), actualFG.value());
    }

    @Test
    public void testBrewgr() {
        SpecificGravity expectedFG = new SpecificGravity(1.01375);

        Brewgr f = new Brewgr();
        SpecificGravity actualFG = f.calc(new GravityPoints(og), yeastAttenuation);

        Assert.assertEquals(expectedFG.value(), actualFG.value());
    }
}

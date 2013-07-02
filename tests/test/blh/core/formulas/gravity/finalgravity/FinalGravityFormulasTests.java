package test.blh.core.formulas.gravity.finalgravity;

import blh.core.formulas.gravity.finalgravity.BYOSimple;
import blh.core.formulas.gravity.finalgravity.Brewgr;
import blh.core.formulas.gravity.finalgravity.Nostrildamus;
import blh.core.units.Factor;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.gravity.SpecificGravity;
import junit.framework.Assert;
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
        GravityPoints actualFG = f.calc(new GravityPoints(og), yeastAttenuation);

        Assert.assertEquals(expectedFG.value(), actualFG.toSpecificGravity().value(), 0.00001);
    }

    @Test
    public void testNostrilDamus() {
        SpecificGravity expectedFG = new SpecificGravity(1.01375);

        Nostrildamus f = new Nostrildamus();
        SpecificGravity actualFG = f.calc(og, yeastAttenuation);

        Assert.assertEquals(expectedFG.value(), actualFG.value(), 0.00001);
    }

    @Test
    public void testBrewgr() {
        SpecificGravity expectedFG = new SpecificGravity(1.01375);

        Brewgr f = new Brewgr();
        SpecificGravity actualFG = f.calc(new GravityPoints(og), yeastAttenuation);

        Assert.assertEquals(expectedFG.value(), actualFG.value(), 0.00001);
    }
}

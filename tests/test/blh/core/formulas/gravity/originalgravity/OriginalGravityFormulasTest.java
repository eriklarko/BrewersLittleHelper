package test.blh.core.formulas.gravity.originalgravity;

import blh.core.formulas.gravity.originalgravity.SimpleOriginalGravityFormula;
import blh.core.units.ExtractPotential;
import blh.core.units.Factor;
import blh.core.units.gravity.GravityPoints;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 7/2/13 11:53 PM
 */
public class OriginalGravityFormulasTest {

    @Test
    public void testSimpleOGFormula() {
        Kilograms grainWeight = new Kilograms(1);
        ExtractPotential extractPotential = new ExtractPotential(new GravityPoints(1), grainWeight);
        Factor extractionEfficiency = new Factor(1);
        Liters volume = new Liters(1);

        SimpleOriginalGravityFormula f = new SimpleOriginalGravityFormula();
        SpecificGravity actualOG = f.calc(grainWeight, extractPotential, extractionEfficiency, volume);

        Assert.fail();
    }
}

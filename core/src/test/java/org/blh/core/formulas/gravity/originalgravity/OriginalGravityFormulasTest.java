package org.blh.core.formulas.gravity.originalgravity;

import org.blh.core.formulas.gravity.originalgravity.SimpleOriginalGravityFormula;
import org.blh.core.ingredients.Malt;
import org.blh.core.recipe.GristPart;
import org.blh.core.units.ExtractPotential;
import org.blh.core.units.Factor;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.volume.USGallons;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Kilograms;
import org.blh.core.units.weight.Lbs;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 7/2/13 11:53 PM
 */
public class OriginalGravityFormulasTest {

    @Test
    /**
     * You plan to use 3.5 lbs. malt extract, plus 4.5 lbs. pale malt and 1/2 lb. 
     * crystal malt (40° Lovibond). Your extraction efficiency, from before, 
     * was 83%. From Table 1, you can see that dried malt extract yields 45 GP/lb. 
     * Extraction efficiency for malt extract is always 100%, so its extraction 
     * efficiency is 1.0. The extract potential for pale malt is 36 GU/lb., 
     * while the EP for the crystal is 30. Substituting the numbers for 
     * the variables gives you:
     *
     *   SGP = [3.5*45*1.00]/5 + [4.5*36 *0.83]/5 + [0.5*30*0.83]/5 = 60.882
     *   -> 1.060882
     * 
     *  45 GP/Lbs = x GP/Kg, 45 / Lbs.CONVERSION_FACTOR
     *  36 GP/Lbs = x GP/Kg
     *  30 GP/Lbs = x GP/Kg
     */
    public void testSimpleOGFormula() {
        Malt mExtract = new Malt(null, null, new ExtractPotential(new GravityPoints(45 / Lbs.CONVERSION_FACTOR), new Kilograms(1)), Malt.TYPE.EXTRACT);
        Malt mPale = new Malt(null, null, new ExtractPotential(new GravityPoints(36 / Lbs.CONVERSION_FACTOR), new Kilograms(1)), Malt.TYPE.GRAIN);
        Malt mCrystal = new Malt(null, null, new ExtractPotential(new GravityPoints(30 / Lbs.CONVERSION_FACTOR), new Kilograms(1)), Malt.TYPE.GRAIN);

        GristPart extract = new GristPart(mExtract, new Lbs(3.5).toKilograms());
        GristPart pale = new GristPart(mPale, new Lbs(4.5).toKilograms());
        GristPart crystal = new GristPart(mCrystal, new Lbs(0.5).toKilograms());
        List<GristPart> gristParts = Arrays.asList(extract, pale, crystal);

        Factor extractionEfficiency = new Factor(0.83);
        USGallons volumeG = new USGallons(5);
        Liters volumeL = volumeG.toLiters();

        SimpleOriginalGravityFormula f = new SimpleOriginalGravityFormula();
        SpecificGravity ogImperial = f.calc(gristParts, volumeG, extractionEfficiency);
        SpecificGravity ogMetric = f.calc(gristParts, volumeL, extractionEfficiency);

        SpecificGravity expected = new SpecificGravity(1.060882);

        Assert.assertEquals("Metric differs", expected.value(), ogMetric.value(), 0.00001);
        Assert.assertEquals("Imperial differs", expected.value(), ogImperial.value(), 0.00001);
    }
}

package org.blh.core.formulas.bitterness;

import org.blh.core.formula.bitterness.Daniels;
import java.util.Arrays;
import java.util.List;

import org.blh.core.ingredient.Hop;
import org.blh.core.recipe.HopAddition;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Grams;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author thinner
 */
@Ignore
public class DanielsTest {

    @Test
    public void testCalc() {
        HopAddition addition1 = new HopAddition(new Hop(null, new Percentage(5)), new Minutes(60), new Grams(56.6990463));
        HopAddition addition2 = new HopAddition(new Hop(null, new Percentage(7)), new Minutes(25), new Grams(85.0485694));
        List<HopAddition> hopAdditions = Arrays.asList(addition1, addition2);

        Liters boilVolume = new Liters(18.9270589);
        SpecificGravity originalGravity = new SpecificGravity(1.050);

        Daniels f = new Daniels();
        IBU actual = f.calc(hopAdditions, originalGravity, boilVolume, boilVolume);
        IBU expected = new IBU(84.98818390360006);

        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testGetIBUsForAddition() {
        Hop hop = new Hop(null, new Percentage(5));
        HopAddition addition = new HopAddition(hop, new Minutes(60), new Grams(56.6990463));

        Liters postBoilVolume = new Liters(18.9270589);
        Liters preFermentationVolume = new Liters(18.9270589);
        SpecificGravity boilGravity = new SpecificGravity(1.050);

        Daniels f = new Daniels();
        IBU actual = f.getIBUsFromAddition(addition.getAmount(), addition.getHop().getAlphaAcids().asFactor(), boilGravity, postBoilVolume, preFermentationVolume);
        IBU expected = new IBU(34.54956542616044);

        Assert.assertEquals(expected.value(), actual.value());
    }
}

package org.blh.core.formulas.bitterness;

import java.util.Arrays;
import java.util.List;

import org.blh.core.ingredients.Hop;
import org.blh.core.recipe.HopAddition;
import org.blh.core.units.Percentage;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.distance.Meters;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.volume.USGallons;
import org.blh.core.units.weight.Grams;
import org.blh.core.units.weight.Oz;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class GaretzTest {

    @Test
    public void testCalc1() {
        Hop hop = new Hop(null, new Percentage(3.6));

        HopAddition addition1 = new HopAddition(hop, new Minutes(75), new Oz(0.75).toGrams());
        HopAddition addition2 = new HopAddition(hop, new Minutes(60), new Oz(0.5).toGrams());
        HopAddition addition3 = new HopAddition(hop, new Minutes(45), new Oz(1).toGrams());
        HopAddition addition4 = new HopAddition(hop, new Minutes(15), new Oz(0.5).toGrams());
        List<HopAddition> hopAdditions = Arrays.asList(addition1, addition2, addition3, addition4);

        testAddition(hopAdditions, new IBU(26.47));
    }

    @Test
    public void testCalc2() {
        HopAddition addition1 = new HopAddition(new Hop(null, new Percentage(8.2)), new Minutes(7), new Oz(0.92).toGrams());
        HopAddition addition2 = new HopAddition(new Hop(null, new Percentage(5.7)), new Minutes(25), new Oz(0.49).toGrams());
        HopAddition addition3 = new HopAddition(new Hop(null, new Percentage(8.2)), new Minutes(10), new Oz(0.46).toGrams());
        HopAddition addition4 = new HopAddition(new Hop(null, new Percentage(8.2)), new Minutes(2), new Oz(0.46).toGrams());
        List<HopAddition> hopAdditions = Arrays.asList(addition1, addition2, addition3, addition4);

        testAddition(hopAdditions, new IBU(22.6));
    }

    public void testAddition(List<HopAddition> hopAdditions, IBU expected) {
        Liters boilVolume = new USGallons(6).toLiters();
        SpecificGravity boilGravity = new SpecificGravity(1.050);
        Meters elevation = new Meters(100);

        Garetz f = new Garetz();
        IBU actual = f.calc(hopAdditions, boilVolume, boilVolume, boilGravity, elevation);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetIBUsForAddition() {
        Hop hop = new Hop(null, new Percentage(5));
        HopAddition addition = new HopAddition(hop, new Minutes(60), new Grams(50));
        Liters boilVolume = new Liters(18);
        Liters finalVolume = new Liters(20);
        SpecificGravity boilGravity = new SpecificGravity(1.050);
        Meters elevation = new Meters(100);

        Garetz f = new Garetz();
        IBU actual = f.getIBUsFromAddition(addition, finalVolume, boilVolume, boilGravity, elevation);
        IBU expected = new IBU(28.6);

        Assert.assertEquals(expected, actual);
    }
}

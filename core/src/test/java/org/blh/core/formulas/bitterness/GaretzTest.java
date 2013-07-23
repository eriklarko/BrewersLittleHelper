package org.blh.core.formulas.bitterness;

import org.blh.core.ingredients.Hop;
import org.blh.core.recipe.HopAddition;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.uncategorized.Input;
import org.blh.core.uncategorized.InputtedOrCalculatedValue;
import org.blh.core.units.Percentage;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.distance.Meters;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Grams;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author thinner
 */
public class GaretzTest {

    @Test
    public void testCalc() {
        HopAddition addition1 = new HopAddition(new Hop(null, new Percentage(5)), new Minutes(60), new Grams(56.6990463));
        HopAddition addition2 = new HopAddition(new Hop(null, new Percentage(7)), new Minutes(25), new Grams(85.0485694));
        List<HopAddition> hopAdditions = Arrays.asList(addition1, addition2);
        
        Liters boilVolume = new Liters(18.9270589);
        SpecificGravity boilGravity = new SpecificGravity(1.050);
        Meters elevation = new Meters(100);
        
        FullContext context = Mockito.mock(FullContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(context.getIngredientsList().getHopAdditions()).thenReturn(hopAdditions);
        Mockito.when(context.getBoilVolumeAtMinutesLeft(Mockito.any(Minutes.class))).thenReturn(boilVolume);
        Mockito.when(context.volumePre(context.FINAL)).thenReturn(boilVolume);
        context.preBoilGravity = new InputtedOrCalculatedValue<>(boilGravity);
        context.elevation = new Input<>(elevation);
        
        Garetz f = new Garetz();
        IBU actual = f.calc(context);
        IBU expected = new IBU(84.98818390360006);
        
        Assert.assertEquals(expected.value(), actual.value(), 0);
    }

    @Test
    public void testGetIBUsForAddition() {
        Hop hop = new Hop(null, new Percentage(5));
        HopAddition addition = new HopAddition(hop, new Minutes(60), new Grams(56.6990463));
        Liters boilVolume = new Liters(18.9270589);
        Liters finalVolume = new Liters(18.9270589);
        SpecificGravity boilGravity = new SpecificGravity(1.050);
        Meters elevation = new Meters(100);
        
        Garetz f = new Garetz();
        IBU actual = f.getIBUsFromAddition(addition, finalVolume, boilVolume, boilGravity, elevation);
        IBU expected = new IBU(34.54956542616044);
        
        Assert.assertEquals(expected.value(), actual.value(), 0);
    }
}

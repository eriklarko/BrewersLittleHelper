package org.blh.core.formulas.yeast.attenuation.apparent;

import java.util.Arrays;
import junit.framework.Assert;
import org.blh.core.formula.yeast.attenuation.apparent.SummedFromIngredientsList;
import org.blh.core.ingredient.Yeast;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.volume.Milliliters;
import org.blh.core.unit.weight.Grams;
import org.junit.Test;

/**
 *
 * @author eriklark
 */
public class SummedFromIngredientsListTest {

    @Test
    public void testOneGramsAddition() {
        SummedFromIngredientsList f = new SummedFromIngredientsList();
        YeastAddition<Grams> addition = new YeastAddition<>(new Yeast(null, null, new Percentage(7)), new Grams(3));

        Percentage expected = new Percentage(7);
        Percentage actual = f.calcDry(Arrays.asList(addition));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testManyGramsAddition() {
        SummedFromIngredientsList f = new SummedFromIngredientsList();
        YeastAddition<Grams> addition1 = new YeastAddition<>(new Yeast(null, null, new Percentage(7)), new Grams(3));
        YeastAddition<Grams> addition2 = new YeastAddition<>(new Yeast(null, null, new Percentage(5)), new Grams(11));

        Percentage expected = new Percentage(1.5 + 3.928571429);
        Percentage actual = f.calcDry(Arrays.asList(addition1, addition2));

        Assert.assertEquals(expected, actual);
    }

        @Test
    public void testOneMillilitresAddition() {
        SummedFromIngredientsList f = new SummedFromIngredientsList();
        YeastAddition<Milliliters> addition = new YeastAddition<>(new Yeast(null, null, new Percentage(7)), new Milliliters(3));

        Percentage expected = new Percentage(7);
        Percentage actual = f.calcLiquid(Arrays.asList(addition));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testManyMillilitresAddition() {
        SummedFromIngredientsList f = new SummedFromIngredientsList();
        YeastAddition<Milliliters> addition1 = new YeastAddition<>(new Yeast(null, null, new Percentage(7)), new Milliliters(3));
        YeastAddition<Milliliters> addition2 = new YeastAddition<>(new Yeast(null, null, new Percentage(5)), new Milliliters(11));

        Percentage expected = new Percentage(1.5 + 3.928571429);
        Percentage actual = f.calcLiquid(Arrays.asList(addition1, addition2));

        Assert.assertEquals(expected, actual);
    }
}

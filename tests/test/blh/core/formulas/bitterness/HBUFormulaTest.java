/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blh.core.formulas.bitterness;

import blh.core.formulas.bitterness.HBUFormula;
import blh.core.ingredients.Hop;
import blh.core.recipe.HopAddition;
import blh.core.uncategorized.FullContext;
import blh.core.units.Percentage;
import blh.core.units.bitterness.HBU;
import blh.core.units.weight.Grams;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author thinner
 */
public class HBUFormulaTest {

    @Test
    /**
     * 
     * For example, if 2 ounces of Northern Brewer hops (9% alpha acid) and 
     * 3 ounces of Cascade hops (5% alpha acid) were used in a 10-gallon batch, 
     * the total amount of bitterness units would be 33 
     *
     *    (2 x 9) + (3 x 5) = 18 + 15 = 33
     */
    public void testCalc() {
        HopAddition addition1 = new HopAddition(new Hop(null, new Percentage(9)), null, new Grams(56.6990463));
        HopAddition addition2 = new HopAddition(new Hop(null, new Percentage(5)), null, new Grams(85.0485694));
        List<HopAddition> hopAdditions = Arrays.asList(addition1, addition2);
        
        FullContext context = Mockito.mock(FullContext.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(context.getIngredientsList().getHopAdditions()).thenReturn(hopAdditions);
        HBUFormula f = new HBUFormula();
        HBU actual = f.calc(context);
        HBU expected = new HBU(33);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }

    @Test
    public void testGetHBUsForAddition() {
        HopAddition addition = new HopAddition(new Hop(null, new Percentage(9)), null, new Grams(56.6990463));
        
        HBUFormula f = new HBUFormula();
        HBU actual = f.getHBUsForAddition(addition);
        HBU expected = new HBU(18);
        
        Assert.assertEquals(expected.value(), actual.value(), 0.00001);
    }
}

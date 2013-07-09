package test.blh.core.formulas.yeast.pitchrate;

import blh.core.units.quantity.Million;
import blh.core.formulas.yeast.pitchrate.FixFormula;
import blh.core.uncategorized.RecipeMetaData.BEER_TYPE;
import blh.core.units.gravity.Plato;
import blh.core.units.quantity.YeastCellCount;
import blh.core.units.volume.Milliliters;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thinner
 */
public class FixTest {
    
   
    @Test
    /**
     * So, for a 1.048 wort pitching into 5.25 gallons you need about 180 billion cells. 
     *  (750,000) X (20,000) X (12) = 180,000,000,000
     * 
     */
    public void testCalc() {
        BEER_TYPE type = BEER_TYPE.ALE;
        YeastCellCount neededCellsCount = new YeastCellCount(new Million(0.75));
        Milliliters amountOfWort = new Milliliters(20_000);
        Plato wortDensity = new Plato(12);

        FixFormula instance = new FixFormula();
        
        YeastCellCount expResult = new YeastCellCount(180);
        YeastCellCount result1 = instance.calc(neededCellsCount, amountOfWort, wortDensity);
        YeastCellCount result2 = instance.calc(type, amountOfWort, wortDensity);
        
        assertEquals("Specific yeast cell count", expResult.value().value(), result1.value().value(), 0.0001);
        assertEquals("Type-determined yeast cell count", expResult.value().value(), result2.value().value(), 0.0001);
    }
}

package org.blh.core.formulas.yeast.pitchrate;

import org.blh.core.formula.yeast.pitchrate.FixFormula;
import org.blh.core.uncategorized.BeerType;
import org.blh.core.unit.gravity.Plato;
import org.blh.core.unit.quantity.Million;
import org.blh.core.unit.quantity.YeastCellCount;
import org.blh.core.unit.volume.Milliliters;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
        BeerType type = BeerType.ALE;
        YeastCellCount neededCellsCount = new YeastCellCount(new Million(0.75));
        Milliliters amountOfWort = new Milliliters(20_000);
        Plato wortDensity = new Plato(12);

        FixFormula instance = new FixFormula();

        YeastCellCount expResult = new YeastCellCount(180);
        YeastCellCount result1 = instance.calc(neededCellsCount, amountOfWort, wortDensity);
        YeastCellCount result2 = instance.calc(type, amountOfWort, wortDensity);

        assertEquals("Specific yeast cell count", expResult.value().value(), result1.value().value());
        assertEquals("Type-determined yeast cell count", expResult.value().value(), result2.value().value());
    }
}

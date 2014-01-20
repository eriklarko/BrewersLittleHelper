package org.blh.core.formula.yeast.pitchrate;

import org.blh.core.uncategorized.BeerType;
import org.blh.core.unit.gravity.Plato;
import org.blh.core.unit.quantity.Billion;
import org.blh.core.unit.quantity.Million;
import org.blh.core.unit.quantity.YeastCellCount;
import org.blh.core.unit.volume.Milliliters;

/**
 * Taken from http://www.mrmalty.com/pitching.php
 *
 * NEEDED CELLS is 0.75 millon for ale, 1.5 millon for lager
 *
 * pitch = (NEEDED CELLS) X (milliliters of wort) X (degrees Plato of the wort)
 *
 * Created by Erik Larkö at 7/4/13 10:57 PM
 */
public class FixFormula {

    private static final YeastCellCount ALE = new YeastCellCount(new Million(0.75));
    private static final YeastCellCount LAGER = new YeastCellCount(new Million(150));

    public YeastCellCount calc(BeerType type, Milliliters amountOfWort, Plato wortDensity) {
        YeastCellCount neededCellsCount;
        if (type == BeerType.ALE) {
            neededCellsCount = ALE;
        } else if (type == BeerType.LAGER) {
            neededCellsCount = LAGER;
        } else {
            throw new IllegalArgumentException("Unknown beer type: " + type);
        }

        return calc(neededCellsCount, amountOfWort, wortDensity);
    }

    public YeastCellCount calc(YeastCellCount neededCellsCount, Milliliters amountOfWort, Plato wortDensity) {
        double cellCountInBillions = neededCellsCount.value().value() * amountOfWort.value() * wortDensity.value();
        return new YeastCellCount(new Billion(cellCountInBillions));
    }
}

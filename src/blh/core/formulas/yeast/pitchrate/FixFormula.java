package blh.core.formulas.yeast.pitchrate;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.uncategorized.RecipeMetaData;
import blh.core.units.gravity.Plato;
import blh.core.units.quantity.Billion;
import blh.core.units.quantity.Million;
import blh.core.units.quantity.YeastCellCount;
import blh.core.units.volume.Milliliters;

/**
 * Taken from http://www.mrmalty.com/pitching.php
 *
 * NEEDED CELLS is 0.75 millon for ale, 1.5 millon for lager
 *
 * pitch = (NEEDED CELLS) X (milliliters of wort) X (degrees Plato of the wort)
 *
 * Created by Erik Larkö at 7/4/13 10:57 PM
 */
public class FixFormula implements Formula<YeastCellCount>  {

    private static YeastCellCount ALE = new YeastCellCount(new Million(0.75));
    private static YeastCellCount LAGER = new YeastCellCount(new Million(150));

    @Override
    public YeastCellCount calc(FullContext context) {
        Milliliters amountOfWort = new Milliliters(context.finalVolume.value());
        Plato wortDensity = new Plato(context.finalGravity.value());

        return calc(context.getRecipeMetaData().type, amountOfWort, wortDensity);
    }
    
    public YeastCellCount calc(RecipeMetaData.BEER_TYPE type, Milliliters amountOfWort, Plato wortDensity) {
        YeastCellCount neededCellsCount;
        if(type == RecipeMetaData.BEER_TYPE.ALE) {
            neededCellsCount = ALE;
        } else if(type == RecipeMetaData.BEER_TYPE.LAGER) {
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

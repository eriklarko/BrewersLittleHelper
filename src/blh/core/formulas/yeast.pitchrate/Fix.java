package blh.core.formulas.yeast.pitchrate;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.uncategorized.RecipeMetaData;
import blh.core.units.gravity.Plato;
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
public class Fix implements Formula<YeastCellCount>  {

    private static YeastCellCount ALE = new YeastCellCount(75_000_000);
    private static YeastCellCount LAGER = new YeastCellCount(150_000_000);

    @Override
    public YeastCellCount calc(FullContext context) {
        YeastCellCount neededCellsCount;
        if(context.getRecipeMetaData().type == RecipeMetaData.BEER_TYPE.ALE) {
            neededCellsCount = ALE;
        } else if(context.getRecipeMetaData().type == RecipeMetaData.BEER_TYPE.LAGER) {
            neededCellsCount = LAGER;
        } else {
            throw new IllegalArgumentException("Unknown beer type: " + context.getRecipeMetaData().type);
        }

        Milliliters amountOfWort = new Milliliters(context.finalVolume.value());
        Plato wortDensity = new Plato(context.finalGravity.value());

        return calc(neededCellsCount, amountOfWort, wortDensity);
    }

    public YeastCellCount calc(YeastCellCount neededCellsCount, Milliliters amountOfWort, Plato wortDensity) {
        int cellCount = (int) (neededCellsCount.value() * amountOfWort.value() * wortDensity.value());
        return new YeastCellCount(cellCount);
    }
}

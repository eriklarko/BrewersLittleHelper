package org.blh.core.recipe;

import org.blh.core.ingredient.Yeast;
import org.blh.core.unit.Unit;

/**
 * Represents a part of the yeasts in a recipe.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @param <T> Should be milliliters or grams.
 */
public class YeastAddition<T extends Unit<? extends Number>> {

    private final Yeast yeast;
    private final T amount;

    public YeastAddition(Yeast yeast, T amount) {
        this.yeast = yeast;
        this.amount = amount;
    }

    public Yeast getYeast() {
        return yeast;
    }

    public T getAmount() {
        return amount;
    }

}

package org.blh.core.formula.yeast.attenuation.apparent;

import java.util.Collection;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.volume.Milliliters;
import org.blh.core.unit.weight.Grams;

/**
 * Source:
 * @author eriklark
 */
public class SummedFromIngredientsList {

    public Percentage calcDry(Collection<YeastAddition<Grams>> yeasts) {
        double totalAmount = 0;
        for (YeastAddition<Grams> yeastAddition : yeasts) {
            totalAmount += yeastAddition.getAmount().value();
        }

        double totalAttenuation = 0;
        for (YeastAddition<Grams> yeastAddition : yeasts) {
            totalAttenuation += (yeastAddition.getAmount().value() / totalAmount) * yeastAddition.getYeast().getAttenuation().value();
        }

        return new Percentage(totalAttenuation);
    }

    public Percentage calcLiquid(Collection<YeastAddition<Milliliters>> yeasts) {
        double totalAmount = 0;
        for (YeastAddition<Milliliters> yeastAddition : yeasts) {
            totalAmount += yeastAddition.getAmount().value();
        }

        double totalAttenuation = 0;
        for (YeastAddition<Milliliters> yeastAddition : yeasts) {
            totalAttenuation += (yeastAddition.getAmount().value() / totalAmount) * yeastAddition.getYeast().getAttenuation().value();
        }

        return new Percentage(totalAttenuation);
    }
}

package blh.core.recipe;


import blh.core.units.ExtractPotential;
import blh.core.units.color.ColorPotential;
import blh.core.units.weight.Grams;
import blh.core.units.weight.Kilograms;
import blh.core.units.weight.Lbs;
import java.util.List;

/**
 *
 * @author thinner
 */
public class IngredientsList {

    private List<GristPart> fermentables;
    private List<HopAddition> hopAdditions;

    public Kilograms getTotalGrainWeight() {
        double totalWeight = 0;
        for (GristPart fermentable : fermentables) {
            totalWeight += fermentable.getMalt().color.value();
        }
        return new Kilograms(totalWeight);
    }

    public ExtractPotential getTotalExtractPotential() {
        int specificGravityPoints = 0;
        for (GristPart fermentable : fermentables) {
            specificGravityPoints += fermentable.getMalt().extractPotential;
        }
        
        
        Kilograms weight = getTotalGrainWeight();

        return new ExtractPotential(specificGravityPoints, weight);
    }

    public ColorPotential getTotalColorPotential() {
        ColorPotential potential = new ColorPotential();
        for (GristPart fermentable : fermentables) {
            potential.add(fermentable.getMalt().color, new Lbs(fermentable.getAmount()));
        }
        return potential;
    }

    public Grams getTotalHopWeight() {
        double totalGrams = 0;
        for (HopAddition addition : hopAdditions) {
            totalGrams += addition.getAmount().value();
        }
        return new Grams(totalGrams);
    }
}

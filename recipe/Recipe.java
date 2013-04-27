package recipe;

import java.util.List;
import units.ExtractPotential;
import units.color.ColorPotential;
import units.weight.Grams;
import units.weight.Kilograms;
import units.weight.Lbs;

/**
 *
 * @author thinner
 */
public class Recipe {

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

package org.blh.recipe;

import java.util.LinkedList;
import java.util.List;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.weight.Grams;
import org.blh.core.units.weight.Kilograms;
import org.blh.core.units.weight.Lbs;

/**
 * TODO: It seems that a lot of things here should be observable...
 * All the calculating methods should be observable imo...
 *
 * @author thinner
 */
public class IngredientsList {

    private List<GristPart> fermentables;
    private List<HopAddition> hopAdditions;
    private List<YeastAddition<?>> yeastAdditions;

    public List<GristPart> getFermentables() {
        return fermentables;
    }

    public List<HopAddition> getHopAdditions() {
        return new LinkedList<>(hopAdditions);
    }

	public List<YeastAddition<?>> getYeastAdditions() {
		return new LinkedList<>(yeastAdditions);
	}

    public Kilograms getTotalGrainWeight() {
        double totalWeight = 0;
        for (GristPart fermentable : fermentables) {
            totalWeight += fermentable.getMalt().getColor().value();
        }
        return new Kilograms(totalWeight);
    }

    public GravityPoints getTotalGravityPoints() {
        double specificGravityPoints = 0;
        for (GristPart fermentable : fermentables) {
            specificGravityPoints += fermentable.getMalt().getExtractPotential().value();
        }

        return new GravityPoints(specificGravityPoints);
    }

    public ColorPotential getTotalColorPotential() {
        ColorPotential potential = new ColorPotential();
        for (GristPart fermentable : fermentables) {
            potential.add(fermentable.getMalt().getColor(), new Lbs(fermentable.getAmount()));
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

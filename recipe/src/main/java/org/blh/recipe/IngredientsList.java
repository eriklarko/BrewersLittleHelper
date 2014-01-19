package org.blh.recipe;

import java.util.Collection;
import java.util.LinkedList;
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

    private final Collection<GristPart> fermentables;
    private final Collection<HopAddition> hopAdditions;
    private final Collection<YeastAddition<?>> yeastAdditions;

	public IngredientsList(Collection<GristPart> fermentables, Collection<HopAddition> hopAdditions, Collection<YeastAddition<?>> yeastAdditions) {
		this.fermentables = fermentables;
		this.hopAdditions = hopAdditions;
		this.yeastAdditions = yeastAdditions;
	}

    public Collection<GristPart> getFermentables() {
        return fermentables;
    }

    public Collection<HopAddition> getHopAdditions() {
        return new LinkedList<>(hopAdditions);
    }

	public Collection<YeastAddition<?>> getYeastAdditions() {
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

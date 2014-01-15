package org.blh.core.recipe;

import java.math.BigDecimal;
import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.weight.Grams;
import org.blh.core.units.weight.Kilograms;
import org.blh.core.units.weight.Lbs;

import java.util.List;
import java.util.LinkedList;

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
        BigDecimal totalWeight = BigDecimal.ZERO;
        for (GristPart fermentable : fermentables) {
            totalWeight = totalWeight.add(fermentable.getMalt().getColor().value());
        }
        return new Kilograms(totalWeight);
    }

    public GravityPoints getTotalGravityPoints() {
        BigDecimal specificGravityPoints = BigDecimal.ZERO;
        for (GristPart fermentable : fermentables) {
            specificGravityPoints = specificGravityPoints.add(fermentable.getMalt().getExtractPotential().value());
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
        BigDecimal totalGrams = BigDecimal.ZERO;
        for (HopAddition addition : hopAdditions) {
            totalGrams = totalGrams.add(addition.getAmount().value());
        }
        return new Grams(totalGrams);
    }
}

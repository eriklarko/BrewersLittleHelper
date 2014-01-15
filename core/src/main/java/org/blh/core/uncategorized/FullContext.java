package org.blh.core.uncategorized;

import java.math.BigDecimal;
import org.blh.core.formulas.volumes.water.BrewStep;
import org.blh.core.formulas.volumes.water.VolumeCalculator;
import org.blh.core.formulas.volumes.water.impl.FinalStep;
import org.blh.core.recipe.IngredientsList;
import org.blh.core.units.Factor;
import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.color.MaltColorUnit;
import org.blh.core.units.distance.Meters;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Grams;
import org.blh.core.units.weight.Kilograms;

/**
 * Should this object be mutable? Or should all its members be mutable?
 *
 * @author thinner
 */
public class FullContext {

	public BrewStep MASH = null;
	public BrewStep BOIL = null;
	public BrewStep COOLING = null;
	public BrewStep FERMENTATION = null;
	public FinalStep FINAL = new FinalStep();

	private IngredientsList recipe;
	private GeneralBreweryInfo brewery;
	private Equipment equipment;
	private RecipeMetaData recipeMetaData;
	private VolumeCalculator vc;
	/////////////
	public InputtedOrCalculatedValue<Liters> preMashVolume;
	public InputtedOrCalculatedValue<Minutes> boilTime;
	public InputtedOrCalculatedValue<SpecificGravity> preBoilGravity;
	public InputtedOrCalculatedValue<SpecificGravity> boilGravity;
	public InputtedOrCalculatedValue<SpecificGravity> postBoilGravity;
	public InputtedOrCalculatedValue<SpecificGravity> originalGravity;
	public InputtedOrCalculatedValue<SpecificGravity> finalGravity;
	public InputtedOrCalculatedValue<ABV> alcoholContent;
	public InputtedOrCalculatedValue<Factor> yeastApparentAttenuation;
	public InputtedOrCalculatedValue<MaltColorUnit> maltColorUnit;
	public InputtedOrCalculatedValue<ColorPotential> totalColorPotential;
	public InputtedOrCalculatedValue<Factor> extractionEfficiency;
	public InputtedOrCalculatedValue<Kilograms> totalGrainWeight;
	public InputtedOrCalculatedValue<Grams> totalHopWeight;
	///////////////
	public Input<Meters> elevation;
	/**
	 * How many percent of the volume is lost when cooling.
	 */
	public Input<Factor> coolingLoss;

	public FullContext() {
	}

	public IngredientsList getIngredientsList() {
		return recipe;
	}

	public GeneralBreweryInfo getBrewery() {
		return brewery;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public RecipeMetaData getRecipeMetaData() {
		return recipeMetaData;
	}

	public Liters getBoilVolumeAtMinutesLeft(Minutes time) {
		BigDecimal timePercent = time.value().divide(boilTime.value().value());
		BigDecimal totalBoilOff = vc.pre(BOIL, this).value().subtract(vc.post(BOIL, this).value());

		return new Liters(totalBoilOff.multiply(timePercent));
	}

	public SpecificGravity getBoilGravityAtMinutesLeft(Minutes time) {
		BigDecimal timePercent = time.value().divide(boilTime.value().value());
		BigDecimal totalGravityDifference = postBoilGravity.value().value().subtract(preBoilGravity.value().value());

		return new SpecificGravity(totalGravityDifference.multiply(timePercent));
	}

	public Liters volumePre(BrewStep step) {
		return vc.pre(step, this);
	}

	public Liters volumePost(BrewStep step) {
		return vc.post(step, this);
	}
}

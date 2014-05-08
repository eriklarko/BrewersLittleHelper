package org.blh.formuladecorator;

import org.blh.core.unit.Factor;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.color.MaltColorUnit;
import org.blh.core.unit.distance.Meters;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.blh.recipe.attempts.composite.Recipe;

/**
 * Should this object be mutable? Or should all its members be mutable?
 *
 * @author thinner
 */
public class FullContext {

    private Recipe recipe;
    private GeneralBreweryInfo brewery;
    private Equipment equipment;
    /////////////
    private NewIOCV<Liters> preMashVolume;
    private NewIOCV<Minutes> boilTime;
    private NewIOCV<SpecificGravity> preBoilGravity;
    private NewIOCV<SpecificGravity> boilGravity;
    private NewIOCV<SpecificGravity> postBoilGravity;
    private NewIOCV<SpecificGravity> originalGravity;
    private NewIOCV<SpecificGravity> finalGravity;
    private NewIOCV<ABV> alcoholContent;
    private NewIOCV<Factor> yeastApparentAttenuation;
    private NewIOCV<MaltColorUnit> maltColorUnit;
    private NewIOCV<ColorPotential> totalColorPotential;
    private NewIOCV<Factor> extractionEfficiency;
    private NewIOCV<Kilograms> totalGrainWeight;
    private NewIOCV<Grams> totalHopWeight;
    ///////////////
    private Input<Meters> elevation;
    /**
     * How many percent of the volume is lost when cooling.
     */
    private Input<Factor> coolingLoss;

    public FullContext() {
		extractionEfficiency = new NewIOCV<>(this, Factor.class, new Factor(0.8));
        originalGravity = new NewIOCV<>(this, SpecificGravity.class, new DecoratedSimpleOriginalGravityFormula());
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public GeneralBreweryInfo getBrewery() {
        return brewery;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public NewIOCV<Liters> getPreMashVolume() {
        return preMashVolume;
    }

    public NewIOCV<Minutes> getBoilTime() {
        return boilTime;
    }

    public NewIOCV<SpecificGravity> getPreBoilGravity() {
        return preBoilGravity;
    }

    public NewIOCV<SpecificGravity> getBoilGravity() {
        return boilGravity;
    }

    public NewIOCV<SpecificGravity> getPostBoilGravity() {
        return postBoilGravity;
    }

    public NewIOCV<SpecificGravity> getOriginalGravity() {
        return originalGravity;
    }

    public NewIOCV<SpecificGravity> getFinalGravity() {
        return finalGravity;
    }

    public NewIOCV<ABV> getAlcoholContent() {
        return alcoholContent;
    }

    public NewIOCV<Factor> getYeastApparentAttenuation() {
        return yeastApparentAttenuation;
    }

    public NewIOCV<MaltColorUnit> getMaltColorUnit() {
        return maltColorUnit;
    }

    public NewIOCV<ColorPotential> getTotalColorPotential() {
        return totalColorPotential;
    }

    public NewIOCV<Factor> getExtractionEfficiency() {
        return extractionEfficiency;
    }

    public NewIOCV<Kilograms> getTotalGrainWeight() {
        return totalGrainWeight;
    }

    public NewIOCV<Grams> getTotalHopWeight() {
        return totalHopWeight;
    }

    public Input<Meters> getElevation() {
        return elevation;
    }

    public Input<Factor> getCoolingLoss() {
        return coolingLoss;
    }

    public SpecificGravity getBoilGravityAtMinutesLeft(Minutes time) {
        double timePercent = time.value() / boilTime.value().value();
        double totalGravityDifference = postBoilGravity.value().value() - preBoilGravity.value().value();

        return new SpecificGravity(totalGravityDifference * timePercent);
    }
}

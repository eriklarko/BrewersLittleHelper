package org.blh.recipe.uncategorized;

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
import org.blh.recipe.volumes.water.BrewStep;
import org.blh.recipe.volumes.water.VolumeCalculator;
import org.blh.recipe.volumes.water.impl.FinalStep;

/**
 * Should this object be mutable? Or should all its members be mutable?
 *
 * @author thinner
 */
public class FullContext {

    public static final BrewStep MASH = null;
    public static final BrewStep BOIL = null;
    public static final BrewStep COOLING = null;
    public static final BrewStep FERMENTATION = null;
    public static final FinalStep FINAL = new FinalStep();

    private Recipe recipe;
    private GeneralBreweryInfo brewery;
    private Equipment equipment;
    private VolumeCalculator vc;
    /////////////
    private InputtedOrCalculatedValue<Liters> preMashVolume;
    private InputtedOrCalculatedValue<Minutes> boilTime;
    private InputtedOrCalculatedValue<SpecificGravity> preBoilGravity;
    private InputtedOrCalculatedValue<SpecificGravity> boilGravity;
    private InputtedOrCalculatedValue<SpecificGravity> postBoilGravity;
    private InputtedOrCalculatedValue<SpecificGravity> originalGravity;
    private InputtedOrCalculatedValue<SpecificGravity> finalGravity;
    private InputtedOrCalculatedValue<ABV> alcoholContent;
    private InputtedOrCalculatedValue<Factor> yeastApparentAttenuation;
    private InputtedOrCalculatedValue<MaltColorUnit> maltColorUnit;
    private InputtedOrCalculatedValue<ColorPotential> totalColorPotential;
    private InputtedOrCalculatedValue<Factor> extractionEfficiency;
    private InputtedOrCalculatedValue<Kilograms> totalGrainWeight;
    private InputtedOrCalculatedValue<Grams> totalHopWeight;
    ///////////////
    private Input<Meters> elevation;
    /**
     * How many percent of the volume is lost when cooling.
     */
    private Input<Factor> coolingLoss;

    public FullContext() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public GeneralBreweryInfo getBrewery() {
        return brewery;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public VolumeCalculator getVc() {
        return vc;
    }

    public InputtedOrCalculatedValue<Liters> getPreMashVolume() {
        return preMashVolume;
    }

    public InputtedOrCalculatedValue<Minutes> getBoilTime() {
        return boilTime;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getPreBoilGravity() {
        return preBoilGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getBoilGravity() {
        return boilGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getPostBoilGravity() {
        return postBoilGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getOriginalGravity() {
        return originalGravity;
    }

    public InputtedOrCalculatedValue<SpecificGravity> getFinalGravity() {
        return finalGravity;
    }

    public InputtedOrCalculatedValue<ABV> getAlcoholContent() {
        return alcoholContent;
    }

    public InputtedOrCalculatedValue<Factor> getYeastApparentAttenuation() {
        return yeastApparentAttenuation;
    }

    public InputtedOrCalculatedValue<MaltColorUnit> getMaltColorUnit() {
        return maltColorUnit;
    }

    public InputtedOrCalculatedValue<ColorPotential> getTotalColorPotential() {
        return totalColorPotential;
    }

    public InputtedOrCalculatedValue<Factor> getExtractionEfficiency() {
        return extractionEfficiency;
    }

    public InputtedOrCalculatedValue<Kilograms> getTotalGrainWeight() {
        return totalGrainWeight;
    }

    public InputtedOrCalculatedValue<Grams> getTotalHopWeight() {
        return totalHopWeight;
    }

    public Input<Meters> getElevation() {
        return elevation;
    }

    public Input<Factor> getCoolingLoss() {
        return coolingLoss;
    }

    public Liters getBoilVolumeAtMinutesLeft(Minutes time) {
        double timePercent = time.value() / boilTime.value().value();
        double totalBoilOff = vc.pre(BOIL, this).value() - vc.post(BOIL, this).value();

        return new Liters(totalBoilOff * timePercent);
    }

    public SpecificGravity getBoilGravityAtMinutesLeft(Minutes time) {
        double timePercent = time.value() / boilTime.value().value();
        double totalGravityDifference = postBoilGravity.value().value() - preBoilGravity.value().value();

        return new SpecificGravity(totalGravityDifference * timePercent);
    }

    public Liters volumePre(BrewStep step) {
        return vc.pre(step, this);
    }

    public Liters volumePost(BrewStep step) {
        return vc.post(step, this);
    }
}

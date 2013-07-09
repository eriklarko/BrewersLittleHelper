package blh.core.uncategorized;

import blh.core.recipe.IngredientsList;
import blh.core.units.ExtractPotential;
import blh.core.units.Factor;
import blh.core.units.alcohol.ABV;
import blh.core.units.color.ColorPotential;
import blh.core.units.color.MaltColorUnit;
import blh.core.units.distance.Meters;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Grams;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class FullContext {

    private IngredientsList recipe;
    private GeneralBreweryInfo brewery;
    private Equipment equipment;
    private RecipeMetaData recipeMetaData;
    /////////////
    public InputtedOrCalculatedValue<Liters> preMashVolume;
    /**
     * The volume added post boil. Probably after cooling
     */
    public InputtedOrCalculatedValue<Liters> topUpVolume;
    public InputtedOrCalculatedValue<Liters> preBoilVolume;
    public InputtedOrCalculatedValue<Liters> boilVolume;
    public InputtedOrCalculatedValue<Liters> boilOffPerHour;
    public InputtedOrCalculatedValue<Liters> postBoilVolume;
    public InputtedOrCalculatedValue<Liters> postCoolingVolume;
    public InputtedOrCalculatedValue<Liters> trubLoss;
    /**
     * The amount of beer extracted after fermentation. I.e; the amount of beer
     * that can be drunk.
     */
    public InputtedOrCalculatedValue<Liters> finalVolume;
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
        double timePercent = time.value() / boilTime.value().value();
        double totalBoilOff = preBoilVolume.value().value() - postBoilVolume.value().value();

        return new Liters(totalBoilOff * timePercent);
    }

    public SpecificGravity getBoilGravityAtMinutesLeft(Minutes time) {
        double timePercent = time.value() / boilTime.value().value();
        double totalGravityDifference = postBoilGravity.value().value() - preBoilGravity.value().value();

        return new SpecificGravity(totalGravityDifference * timePercent);
    }
}

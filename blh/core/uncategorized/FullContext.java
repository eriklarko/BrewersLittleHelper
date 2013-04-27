package blh.core.uncategorized;

import blh.core.recipe.Recipe;
import blh.core.units.ExtractPotential;
import blh.core.units.alcohol.ABV;
import blh.core.units.color.MaltColorUnit;
import blh.core.units.distance.Meters;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Gallons;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Grams;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class FullContext {

    private Recipe recipe;
    private GeneralBreweryInfo brewery;
    private Equipment equipment;
    ///////////
    private Calculator calculator;

    public Recipe getRecipe() {
        return recipe;
    }

    public GeneralBreweryInfo getBrewery() {
        return brewery;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Liters getPreBoilVolume() {
        // TODO: To be calulated from the mash and things
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Liters getBoilVolume() {
        // TODO: An average of the volume across the boil.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Liters getBoilVolumeWithMinutesLeft(Minutes time) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Liters getFinalVolume() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public SpecificGravity getPreBoilGravity() {
        return calculator.calulatePrePoilGravity(this);
    }

    public SpecificGravity getBoilGravity() {
        // Should be a mean of the gravity across the boil.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public SpecificGravity getBoilGravityWithMinutesLeft(Minutes time) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public SpecificGravity getPostBoilGravity() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public SpecificGravity getOriginalGravity() {
        return calculator.calculateOriginalGravity(this);
    }

    public SpecificGravity getFinalGravity() {
        return calculator.calculateFinalGravity(this);
    }

    public ABV getAlcoholContent() {
        return calculator.calculateABV(this);
    }

    public double getYeastApparentAttenuation() {
        // TODO: The attenuation for just one yeast is simple, but for multiple? And for adding yeast in secondary? Or at other times? Is time a factor?
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public double getExtractionEffiency() {
        return brewery.getEffiency();
    }

    public Kilograms getTotalGrainWeight() {
        return recipe.getTotalGrainWeight();
    }

    public Grams getTotalHopWeight() {
        return recipe.getTotalHopWeight();
    }

    public ExtractPotential getTotalExtractPotential() {
        return recipe.getTotalExtractPotential();
    }

    public Meters getElevation() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public MaltColorUnit getMaltColorUnit() {
        return new MaltColorUnit(recipe.getTotalColorPotential(), new Gallons(this.getFinalVolume()));
    }
}

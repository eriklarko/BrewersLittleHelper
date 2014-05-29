package se.angstroms.blh.anders.uncategorized.context;

import se.angstroms.blh.anders.uncategorized.value.annot.ValueAnnot;
import javafx.collections.FXCollections;
import org.blh.core.uncategorized.BeerType;
import org.blh.core.unit.Factor;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.color.MaltColorUnit;
import org.blh.core.unit.distance.Meters;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.blh.recipe.attempts.composite.BasicRecipe;
import org.blh.recipe.attempts.composite.Recipe;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.InstructionsList;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.InputtedValue;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 * Should this object be mutable? Or should all its members be mutable?
 *
 * @author thinner
 */
public class FullContext {

    private final Recipe recipe = new BasicRecipe(
            new IngredientsList(),
            new InstructionsList(),
            BeerType.ALE, null);
    private final GeneralBreweryInfo brewery = new GeneralBreweryInfo();
    private final Equipment equipment = new Equipment();
    /////////////
    private final InputtedOrCalculatedValue<Liters> preMashVolume = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.BOIL_TIME)
    private final InputtedValue<Minutes> boilTime = new InputtedValue<>();
    private final InputtedOrCalculatedValue<SpecificGravity> preBoilGravity = new InputtedOrCalculatedValue<>();
    private final InputtedOrCalculatedValue<SpecificGravity> boilGravity = new InputtedOrCalculatedValue<>();
    private final InputtedOrCalculatedValue<SpecificGravity> postBoilGravity = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.OG)
    private final InputtedOrCalculatedValue<SpecificGravity> originalGravity = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.FG)
    private final InputtedOrCalculatedValue<SpecificGravity> finalGravity = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.ALCOHOL_CONTENT)
    private final InputtedOrCalculatedValue<ABV> alcoholContent = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.YEAST_ATTENUATION)
    private final InputtedOrCalculatedValue<Factor> yeastApparentAttenuation = new InputtedOrCalculatedValue<>();
    private final InputtedOrCalculatedValue<MaltColorUnit> maltColorUnit = new InputtedOrCalculatedValue<>();
    private final InputtedOrCalculatedValue<ColorPotential> totalColorPotential = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.EXTRACTION_EFFICIENCY)
    private final InputtedOrCalculatedValue<Factor> extractionEfficiency = new InputtedOrCalculatedValue<>();

    private final InputtedOrCalculatedValue<Kilograms> totalGrainWeight = new InputtedOrCalculatedValue<>();
    private final InputtedOrCalculatedValue<Grams> totalHopWeight = new InputtedOrCalculatedValue<>();

    @ValueAnnot(id = ValueId.BITTERNESS)
    private final InputtedOrCalculatedValue<IBU> bitterness = new InputtedOrCalculatedValue<>();
    ///////////////
    @ValueAnnot(id = ValueId.ELEVATION)
    private final InputtedValue<Meters> elevation = new InputtedValue<>();
    /**
     * How many percent of the volume is lost when cooling.
     */
    @ValueAnnot(id = ValueId.COOLING_LOSS)
    private final InputtedValue<Factor> coolingLoss = new InputtedValue<>();

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe.getIngredientsList().setFermentables(recipe.getIngredientsList().getFermentables());
        this.recipe.getIngredientsList().setHopAdditions(recipe.getIngredientsList().getHopAdditions());
        this.recipe.getIngredientsList().setYeastAdditions(recipe.getIngredientsList().getYeastAdditions());

        // TODO: Merge recipes
    }

    public GeneralBreweryInfo getBrewery() {
        return brewery;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public InputtedOrCalculatedValue<Liters> getPreMashVolume() {
        return preMashVolume;
    }

    public InputtedValue<Minutes> getBoilTime() {
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

    public InputtedValue<Meters> getElevation() {
        return elevation;
    }

    public InputtedValue<Factor> getCoolingLoss() {
        return coolingLoss;
    }

    public SpecificGravity getBoilGravityAtMinutesLeft(Minutes time) {
        double timePercent = time.value() / boilTime.get().value();
        double totalGravityDifference = postBoilGravity.get().value() - preBoilGravity.get().value();

        return new SpecificGravity(totalGravityDifference * timePercent);
    }
}

package org.blh.recipe.uncategorized;

/**
 * Describes a beer recipe. The what and the how, so to speak.
 *
 * @author Thinner
 */
public class Recipe {

    private final IngredientsList ingredientsList;
    private final InstructionsList instructionsList;
    private final RecipeMetaData metaData;

    public Recipe(IngredientsList ingredientsList, InstructionsList instructionsList, RecipeMetaData metaData) {
        this.ingredientsList = ingredientsList;
        this.instructionsList = instructionsList;
        this.metaData = metaData;
    }

    public IngredientsList getIngredientsList() {
        return ingredientsList;
    }

    public InstructionsList getInstructionsList() {
        return instructionsList;
    }

    public RecipeMetaData getMetaData() {
        return metaData;
    }
}

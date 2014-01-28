package org.blh.recipe.uncategorized;

import org.blh.core.uncategorized.BeerType;

/**
 * Describes a beer recipe. The what and the how, so to speak.
 *
 * @author Thinner
 */
public class Recipe {

    private final IngredientsList ingredientsList;
    private final InstructionsList instructionsList;
    private final BeerType type;
    private final String name;

    public Recipe(IngredientsList ingredientsList, InstructionsList instructionsList, BeerType type, String name) {
        this.ingredientsList = ingredientsList;
        this.instructionsList = instructionsList;
        this.type = type;
        this.name = name;
    }

    public IngredientsList getIngredientsList() {
        return ingredientsList;
    }

    public InstructionsList getInstructionsList() {
        return instructionsList;
    }

    public BeerType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

package org.blh.recipe.attempts.composite;

import org.blh.core.uncategorized.BeerType;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.InstructionsList;

/**
 * Describes a beer recipe. The what and the how, so to speak.
 *
 * @author Thinner
 */
public class BasicRecipe implements Recipe {

    private final IngredientsList ingredientsList;
    private final InstructionsList instructionsList;
    private final BeerType type;
    private final String name;

    public BasicRecipe(IngredientsList ingredientsList, InstructionsList instructionsList, BeerType type, String name) {
        this.ingredientsList = ingredientsList;
        this.instructionsList = instructionsList;
        this.type = type;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BeerType getType() {
        return type;
    }

    @Override
    public IngredientsList getIngredientsList() {
        return ingredientsList;
    }

    @Override
    public InstructionsList getInstructionsList() {
        return instructionsList;
    }
}

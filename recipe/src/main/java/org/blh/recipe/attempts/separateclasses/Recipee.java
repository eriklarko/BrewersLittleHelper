package org.blh.recipe.attempts.separateclasses;

import org.blh.core.uncategorized.BeerType;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.InstructionsList;

/**
 * Describes a beer recipe. The what and the how, so to speak.
 *
 * @author Thinner
 */
public class Recipee {

    private final IngredientsList ingredientsList;
    private final InstructionsList instructionsList;
    private final BeerType type;
    private final String name;

    public Recipee(String name, BeerType type, IngredientsList ingredientsList, InstructionsList instructionsList) {
        this.ingredientsList = ingredientsList;
        this.instructionsList = instructionsList;
        this.type = type;
        this.name = name;
    }

    public BeerType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public IngredientsList getIngredientsList() {
        return ingredientsList;
    }

    public InstructionsList getInstructionsList() {
        return instructionsList;
    }
}

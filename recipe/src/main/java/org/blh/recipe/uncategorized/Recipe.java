package org.blh.recipe.uncategorized;

import org.blh.core.uncategorized.RecipeMetaData;
import org.blh.recipe.IngredientsList;
import org.blh.recipe.InstructionsList;

/**
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

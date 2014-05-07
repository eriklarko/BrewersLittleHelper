package org.blh.recipe.attempts.composite;

import org.blh.core.uncategorized.BeerType;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.InstructionsList;

/**
 * Describes a beer recipe. The what and the how, so to speak.
 *
 * @author Thinner
 */
public interface Recipe {

    String getName();

    BeerType getType();

    IngredientsList getIngredientsList();

    InstructionsList getInstructionsList();
}

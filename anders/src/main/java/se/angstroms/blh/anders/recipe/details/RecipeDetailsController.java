package se.angstroms.blh.anders.recipe.details;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.blh.recipe.uncategorized.Recipe;
import se.angstroms.blh.anders.common.customcontrol.CustomControl;

/**
 * Shows the details of a recipe
 *
 * @author Thinner
 */
public class RecipeDetailsController extends HBox {

	@FXML private IngredientsListController ingredientsList;

	public RecipeDetailsController() {
		CustomControl.setup(this);
	}

	public void setRecipe(Recipe recipe) {
		// TODO: Use progress indication here.
		this.ingredientsList.setIngredientsList(recipe.getIngredientsList());
	}
}

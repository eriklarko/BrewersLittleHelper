package se.angstroms.blh.anders.view.recipe.details;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.blh.recipe.uncategorized.Recipe;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Shows the details of a recipe
 *
 * @author Thinner
 */
public class RecipeDetailsPresenter extends HBox {

    @FXML private IngredientsListPresenter ingredientsList;

    public RecipeDetailsPresenter() {
        CustomControl.setup(this);
    }

    public void setRecipe(Recipe recipe) {
        // TODO: Use progress indication here.
        this.ingredientsList.setIngredientsList(recipe.getIngredientsList());
    }
}

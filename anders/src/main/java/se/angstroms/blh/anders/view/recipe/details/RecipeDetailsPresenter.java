package se.angstroms.blh.anders.view.recipe.details;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.blh.recipe.uncategorized.Recipe;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Shows the details of a recipe
 *
 * @author Thinner
 */
public class RecipeDetailsPresenter extends VBox {

    @FXML private Label recipeName;
    @FXML private IngredientsListPresenter ingredientsList;
    @FXML private RecipeValuesPresenter recipeValues;

    public RecipeDetailsPresenter() {
        CustomControl.setup(this);
    }

    public void setRecipe(Recipe recipe) {
        // TODO: Use progress indication here.
        this.ingredientsList.setIngredientsList(recipe.getIngredientsList());
        this.recipeName.setText(recipe.getName());
        this.recipeValues.recalculateValues();
    }
}

package se.angstroms.blh.anders.view.recipe;

import javafx.beans.property.ListProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.view.recipe.details.RecipeDetailsPresenter;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelectorPresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipesTab extends BorderPane {

	private final RecipeSelectorPresenter recipeSelector = new RecipeSelectorPresenter();
    private final RecipeDetailsPresenter recipeDetails = new RecipeDetailsPresenter();

	public RecipesTab() {
		CustomControl.setup(this);

		recipeSelector.selectedRecipeProperty().addListener((ObservableValue<? extends FullContext> ov, FullContext oldValue, FullContext newValue) -> {
			recipeDetails.recipeProperty().set(newValue);
            if (oldValue != newValue) {
				showRecipeDetails();
			}
        });

		showRecipeList();
	}

	private void showRecipeList() {
		this.getChildren().clear();
		this.setCenter(recipeSelector);
    }

    private void showRecipeDetails() {
		this.getChildren().clear();
		this.setCenter(recipeDetails);
    }

    public ListProperty<FullContext> availableRecipesProperty() {
        return recipeSelector.availableRecipesProperty();
    }
}

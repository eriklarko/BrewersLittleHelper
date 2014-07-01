package se.angstroms.blh.anders.view.recipe;

import javafx.beans.property.ListProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.view.recipe.details.RecipeDetailsPresenter;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelectorPresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipesTab extends VBox {

    @FXML
	private RecipeSelectorPresenter recipeSelector;

	@FXML
    private RecipeDetailsPresenter recipeDetails;

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
		this.getChildren().add(recipeSelector);
    }

    private void showRecipeDetails() {
		this.getChildren().clear();
		this.getChildren().add(recipeDetails);
    }

    public ListProperty<FullContext> availableRecipesProperty() {
        return recipeSelector.availableRecipesProperty();
    }
}

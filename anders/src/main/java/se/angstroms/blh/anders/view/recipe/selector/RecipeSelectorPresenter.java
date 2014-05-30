package se.angstroms.blh.anders.view.recipe.selector;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import se.angstroms.blh.anders.view.util.CustomControl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.view.recipe.selector.grid.RecipeGridPresenter;
import se.angstroms.blh.anders.view.recipe.selector.list.RecipeListPresenter;

/**
 *
 * @author Thinner
 */
public class RecipeSelectorPresenter extends HBox {

	@FXML
	private Pane selectorPane;

	private final ListProperty<FullContext> availableRecipes;
	private final ObjectProperty<FullContext> selectedRecipe;
	private final Collection<RecipeSelector> selectors;

    public RecipeSelectorPresenter() {
        CustomControl.setup(this);

		availableRecipes = new SimpleListProperty<>();
		selectedRecipe = new SimpleObjectProperty<>();
		selectors = new LinkedList<>(Arrays.asList(new RecipeGridPresenter(), new RecipeListPresenter()));

		bindProperties();
		showSelector(selectors.iterator().next());
    }

	private void bindProperties() {
		for (RecipeSelector recipeSelector : selectors) {
			recipeSelector.availableRecipesProperty().bind(this.availableRecipes);
			recipeSelector.selectedRecipeProperty().bindBidirectional(this.selectedRecipe);
		}
	}

	private void showSelector(RecipeSelector selector) {
		selectorPane.getChildren().clear();
		selectorPane.getChildren().add(selector.getGUI());
	}

	public ListProperty<FullContext> availableRecipesProperty() {
		return availableRecipes;
	}

    public ObjectProperty<FullContext> selectedRecipeProperty() {
        return selectedRecipe;
    }

}

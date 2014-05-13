package se.angstroms.blh.anders.view.recipe.selector.grid;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.blh.recipe.attempts.composite.Recipe;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelector;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipeGridPresenter extends GridPane implements RecipeSelector {

	private final ListProperty<Recipe> availableRecipes;
	private final ObjectProperty<Recipe> selectedRecipe;

	public RecipeGridPresenter() {
		CustomControl.setup(this);

		availableRecipes = new SimpleListProperty<>();
		selectedRecipe = new SimpleObjectProperty<>();
	}

	@Override
	public String getName() {
		return "Grid";
	}

	@Override
	public ListProperty<Recipe> availableRecipesProperty() {
		return availableRecipes;
	}

	@Override
	public ObjectProperty<Recipe> selectedRecipeProperty() {
		return selectedRecipe;
	}

	@Override
	public Node getGUI() {
		return this;
	}
}

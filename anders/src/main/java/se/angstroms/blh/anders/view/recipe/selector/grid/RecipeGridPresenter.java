package se.angstroms.blh.anders.view.recipe.selector.grid;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.blh.recipe.attempts.composite.Recipe;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelector;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipeGridPresenter extends FlowPane implements RecipeSelector {

	private final ListProperty<Recipe> availableRecipes;
	private final ObjectProperty<Recipe> selectedRecipe;
	private final EventHandler<MouseEvent> selectRecipeHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			selectedRecipe.set(((GridButton) t.getSource()).getRecipe());
		}
	};

	public RecipeGridPresenter() {
		CustomControl.setup(this);

		availableRecipes = new SimpleListProperty<>();
		selectedRecipe = new SimpleObjectProperty<>();

		availableRecipes.addListener(new ChangeListener<ObservableList<Recipe>>() {

			@Override
			public void changed(ObservableValue<? extends ObservableList<Recipe>> ov, ObservableList<Recipe> t, ObservableList<Recipe> newRecipes) {
				setRecipes(newRecipes);
			}
		});
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

	private void setRecipes(ObservableList<Recipe> recipes) {
		this.getChildren().clear();
		recipes.stream().forEach((recipe) -> {
			GridButton gridButton = new GridButton(recipe);
			gridButton.setOnMouseClicked(selectRecipeHandler);
			this.getChildren().add(gridButton);
		});
	}
}

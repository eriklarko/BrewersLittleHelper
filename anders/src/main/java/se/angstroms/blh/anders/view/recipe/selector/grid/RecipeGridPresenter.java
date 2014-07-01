package se.angstroms.blh.anders.view.recipe.selector.grid;

import com.airhacks.afterburner.injection.InjectionProvider;
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
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelector;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipeGridPresenter extends FlowPane implements RecipeSelector {

	private final ListProperty<FullContext> availableRecipes;
	private final ObjectProperty<FullContext> selectedRecipe;
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

		availableRecipes.addListener(new ChangeListener<ObservableList<FullContext>>() {

			@Override
			public void changed(ObservableValue<? extends ObservableList<FullContext>> ov, ObservableList<FullContext> t, ObservableList<FullContext> newRecipes) {
				setRecipes(newRecipes);
			}
		});
	}

	@Override
	public String getName() {
		return "Grid";
	}

	@Override
	public ListProperty<FullContext> availableRecipesProperty() {
		return availableRecipes;
	}

	@Override
	public ObjectProperty<FullContext> selectedRecipeProperty() {
		return selectedRecipe;
	}

	@Override
	public Node getGUI() {
		return this;
	}

	private void setRecipes(ObservableList<FullContext> recipes) {
		this.getChildren().clear();
		recipes.stream().forEach((recipe) -> {
			GridButton gridButton = new GridButton(recipe);
            InjectionProvider.injectMembers(GridButton.class, gridButton);
			gridButton.setOnMouseClicked(selectRecipeHandler);
            gridButton.render();
			this.getChildren().add(gridButton);
		});
	}
}

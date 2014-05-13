package se.angstroms.blh.anders.view.recipe.selector.list;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import se.angstroms.blh.anders.view.util.CustomControl;
import se.angstroms.blh.anders.view.util.DoubleClickableCellFactory;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.blh.recipe.attempts.composite.Recipe;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelector;

/**
 * Lists some recipes
 *
 * @author Thinner
 */
public class RecipeListPresenter extends TableView<Recipe>  implements RecipeSelector, DoubleClickableCellFactory.DoubleClickListener {

    @FXML
    private TableColumn<Recipe, String> recipeName;

	private final ListProperty<Recipe> availableRecipes;
    private final ObjectProperty<Recipe> selectedRecipe;

    public RecipeListPresenter() {
        CustomControl.setup(this);
        this.setEditable(false);

		availableRecipes = new SimpleListProperty<>();
		selectedRecipe = new SimpleObjectProperty<>();
        recipeName.setCellFactory(new DoubleClickableCellFactory<>(recipeName.getCellFactory(), this));
        recipeName.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> cdf) -> new SimpleStringProperty(cdf.getValue().getName()));

		availableRecipes.addListener(new ChangeListener<ObservableList<Recipe>>() {

			@Override
			public void changed(ObservableValue<? extends ObservableList<Recipe>> ov, ObservableList<Recipe> t, ObservableList<Recipe> newRecipes) {
				RecipeListPresenter.this.setItems(newRecipes);
			}
		});
    }

	@Override
	public String getName() {
		return "List";
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

    @Override
    public void onDoubleClick(MouseEvent event) {
        Recipe recipe = this.getSelectionModel().getSelectedItem();
        this.selectedRecipe.setValue(recipe);
    }

}

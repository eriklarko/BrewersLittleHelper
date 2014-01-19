package se.angstroms.blh.anders.recipe.list;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.blh.recipe.uncategorized.Recipe;
import se.angstroms.blh.anders.common.DoubleClickableCellFactory;
import se.angstroms.blh.anders.common.customcontrol.CustomControl;

/**
 * Lists some recipes
 *
 * @author Thinner
 */
public class RecipeListController extends TableView<Recipe> implements DoubleClickableCellFactory.DoubleClickListener {

	public static interface RecipeSelectedListener {

		void recipeSelected(Recipe recipe);
	}

	@FXML
	private TableColumn<Recipe, String> recipeName;
	private final ReadOnlyObjectWrapper<Recipe> selectedRecipe;

	public RecipeListController() {
		selectedRecipe = new ReadOnlyObjectWrapper<>();

		CustomControl.setup(this);
		this.setEditable(false);

		recipeName.setCellFactory(new DoubleClickableCellFactory<>(recipeName.getCellFactory(), this));
		recipeName.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> cdf) -> new SimpleStringProperty(cdf.getValue().getMetaData().getName()));
	}

	public void setRecipes(ObservableList<Recipe> recipes) {
		this.setItems(recipes);
	}

	public ReadOnlyObjectProperty<Recipe> getSelectedRecipeProperty() {
		return selectedRecipe.getReadOnlyProperty();
	}

	@Override
	public void onDoubleClick(MouseEvent event) {
		Recipe recipe = this.getSelectionModel().getSelectedItem();
		this.selectedRecipe.setValue(recipe);
	}
}

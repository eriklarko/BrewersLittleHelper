package se.angstroms.blh.anders.view.recipe.list;


import se.angstroms.blh.anders.view.util.CustomControl;
import se.angstroms.blh.anders.view.util.DoubleClickableCellFactory;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.blh.recipe.attempts.composite.Recipe;

/**
 * Lists some recipes
 *
 * @author Thinner
 */
public class RecipeListPresenter extends TableView<Recipe> implements DoubleClickableCellFactory.DoubleClickListener {

    public static interface RecipeSelectedListener {

        void recipeSelected(Recipe recipe);
    }

    @FXML
    private TableColumn<Recipe, String> recipeName;
    private final ReadOnlyObjectWrapper<Recipe> selectedRecipe;

    public RecipeListPresenter() {
        selectedRecipe = new ReadOnlyObjectWrapper<>();

        CustomControl.setup(this);
        this.setEditable(false);

        recipeName.setCellFactory(new DoubleClickableCellFactory<>(recipeName.getCellFactory(), this));
        recipeName.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> cdf) -> new SimpleStringProperty(cdf.getValue().getName()));
    }

    public void setRecipes(ObservableList<Recipe> recipes) {
        this.setItems(recipes);
    }

    public ReadOnlyObjectProperty<Recipe> selectedRecipeProperty() {
        return selectedRecipe.getReadOnlyProperty();
    }

    @Override
    public void onDoubleClick(MouseEvent event) {
        Recipe recipe = this.getSelectionModel().getSelectedItem();
        this.selectedRecipe.setValue(recipe);
    }
}

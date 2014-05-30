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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelector;

/**
 * Lists some recipes
 *
 * @author Thinner
 */
public class RecipeListPresenter extends TableView<FullContext> implements RecipeSelector, DoubleClickableCellFactory.DoubleClickListener {

    @FXML
    private TableColumn<FullContext, String> recipeName;

	private final ListProperty<FullContext> availableRecipes;
    private final ObjectProperty<FullContext> selectedRecipe;

    public RecipeListPresenter() {
        CustomControl.setup(this);
        this.setEditable(false);

		availableRecipes = new SimpleListProperty<>();
		selectedRecipe = new SimpleObjectProperty<>();
        recipeName.setCellFactory(new DoubleClickableCellFactory<>(recipeName.getCellFactory(), this));
        recipeName.setCellValueFactory((TableColumn.CellDataFeatures<FullContext, String> cdf) -> new SimpleStringProperty(cdf.getValue().nameProperty().get()));

		availableRecipes.addListener(new ChangeListener<ObservableList<FullContext>>() {

			@Override
			public void changed(ObservableValue<? extends ObservableList<FullContext>> ov, ObservableList<FullContext> t, ObservableList<FullContext> newRecipes) {
				RecipeListPresenter.this.setItems(newRecipes);
			}
		});
    }

	@Override
	public String getName() {
		return "List";
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

    @Override
    public void onDoubleClick(MouseEvent event) {
        FullContext recipe = this.getSelectionModel().getSelectedItem();
        this.selectedRecipe.setValue(recipe);
    }

}

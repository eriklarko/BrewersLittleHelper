package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.inject.Inject;
import org.blh.core.recipe.YeastAddition;
import org.blh.recipe.uncategorized.IngredientsList;
import se.angstroms.blh.anders.data.HopStore;
import se.angstroms.blh.anders.data.MaltStore;
import se.angstroms.blh.anders.data.YeastStore;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Lists the specified ingredients
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class IngredientsListPresenter extends GridPane {

    @Inject private MaltStore maltStore;
    @Inject private HopStore hopStore;
    @Inject private YeastStore yeastStore;

    @FXML private GridListView fermentablesTable;

    @FXML private GridListView hopsTable;

    @FXML private GridListView yeastsTable;
    private TableColumn<YeastAddition<?>, String> yeastsName;
    private TableColumn<YeastAddition<?>, String> yeastsAmount;

    @FXML private TableView<String> othersTable;

	private final ObjectProperty<IngredientsList> ingredientsListProperty;

    public IngredientsListPresenter() {
        CustomControl.setup(this);

		ingredientsListProperty = new SimpleObjectProperty<>();
		ingredientsListProperty.addListener(new ChangeListener<IngredientsList>() {

			@Override
			public void changed(ObservableValue<? extends IngredientsList> ov, IngredientsList t, IngredientsList newValue) {
				setIngredientsList(newValue);
			}
		});

        this.getColumnConstraints().addAll(
                columnConstraintsWithPercentageWidth(25),
                columnConstraintsWithPercentageWidth(25),
                columnConstraintsWithPercentageWidth(25),
                columnConstraintsWithPercentageWidth(25)
        );
    }

    private ColumnConstraints columnConstraintsWithPercentageWidth(double percentage) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percentage);
        return columnConstraints;
    }

	public ObjectProperty<IngredientsList> ingredientsListProperty() {
		return ingredientsListProperty;
	}

    private void setIngredientsList(IngredientsList ingredientsList) {
        // I need to copy the lists here so that sorting the tables won't cause
        // the formulas depending on the ingredients to be recalculated.

        fermentablesTable.setData(FXCollections.observableArrayList(GridListView.toGridRows(ingredientsList.getFermentables(), (model) -> new MaltListItem(model, maltStore))));
        hopsTable.setData(FXCollections.observableArrayList(GridListView.toGridRows(ingredientsList.getHopAdditions(), (model) -> new HopListItem(model, hopStore))));
        yeastsTable.setData(FXCollections.observableArrayList(GridListView.toGridRows(ingredientsList.getYeastAdditions(), (model) -> new YeastListItem(model, yeastStore))));
        //yeastsTable.setItems(FXCollections.observableList(ingredientsList.getYeastAdditions()));
    }
}

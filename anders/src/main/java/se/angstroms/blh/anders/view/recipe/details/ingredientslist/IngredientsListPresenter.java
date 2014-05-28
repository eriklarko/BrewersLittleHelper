package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.recipe.uncategorized.IngredientsList;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Lists the specified ingredients
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class IngredientsListPresenter extends GridPane {

    @FXML private TableView<GristPart> fermentablesTable;
    @FXML private TableColumn<GristPart, String> fermentablesName;
    @FXML private TableColumn<GristPart, String> fermentablesAmount;

    @FXML private TableView<HopAddition> hopsTable;
    @FXML private TableColumn<HopAddition, String> hopsName;
    @FXML private TableColumn<HopAddition, String> hopsAmount;
    @FXML private TableColumn<HopAddition, String> hopsAlphaAcids;
    @FXML private TableColumn<HopAddition, String> hopsTimeInBoil;

    @FXML private TableView<YeastAddition<?>> yeastsTable;
    @FXML private TableColumn<YeastAddition<?>, String> yeastsName;
    @FXML private TableColumn<YeastAddition<?>, String> yeastsAmount;

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

        buildFermentablesTable();
        buildHopsTable();
        buildYeastsTable();
    }

    private void buildFermentablesTable() {
        fermentablesName.setCellValueFactory(
                (TableColumn.CellDataFeatures<GristPart, String> p) -> new SimpleStringProperty(p.getValue().getMalt().getName())
        );
        fermentablesAmount.setCellValueFactory(
                (TableColumn.CellDataFeatures<GristPart, String> p) -> new SimpleStringProperty(p.getValue().getAmount().toString())
        );
    }

    private void buildHopsTable() {
        hopsName.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getHop().getName())
        );
        hopsAmount.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getAmount().value().toString())
        );
        hopsAlphaAcids.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getHop().getAlphaAcids().toString())
        );
        hopsTimeInBoil.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getTimeInBoil().value().toString())
        );
    }

    private void buildYeastsTable() {
        yeastsName.setCellValueFactory(
                (TableColumn.CellDataFeatures<YeastAddition<?>, String> p) -> new SimpleStringProperty(p.getValue().getYeast().getName())
        );
        yeastsAmount.setCellValueFactory(
                (TableColumn.CellDataFeatures<YeastAddition<?>, String> p) -> new SimpleStringProperty(p.getValue().getAmount().toString())
        );
    }

	public ObjectProperty<IngredientsList> ingredientsListProperty() {
		return ingredientsListProperty;
	}

    private void setIngredientsList(IngredientsList ingredientsList) {
        ObservableList<GristPart> gristParts = FXCollections.observableArrayList(ingredientsList.getFermentables());
        ObservableList<HopAddition> hopAdditions = FXCollections.observableArrayList(ingredientsList.getHopAdditions());
        ObservableList<YeastAddition<?>> yeastAdditions = FXCollections.observableArrayList(ingredientsList.getYeastAdditions());

        fermentablesTable.setItems(gristParts);
        hopsTable.setItems(hopAdditions);
        yeastsTable.setItems(yeastAdditions);
    }
}

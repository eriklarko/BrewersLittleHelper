package se.angstroms.blh.anders.recipelist;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.recipe.IngredientsList;
import se.angstroms.blh.anders.common.customcontrol.CustomControl;

/**
 * FXML Controller class
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class RecipeListController extends HBox {

	@FXML
	private TableView<GristPart> fermentablesTable;
	@FXML
	private TableView<HopAddition> hopsTable;
	@FXML
	private TableView<YeastAddition<?>> yeastsTable;
	@FXML
	private TableView<String> othersTable;

	public RecipeListController() {
		CustomControl.setup(this, "RecipeList.fxml");

		buildFermentablesTable();
		buildHopsTable();
		buildYeastsTable();
	}

	private void buildFermentablesTable() {
		TableColumn<GristPart, String> name = new TableColumn("Name");
		TableColumn<GristPart, Number> amount = new TableColumn("Amount");

		name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GristPart, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<GristPart, String> p) {
				return new SimpleStringProperty(p.getValue().getMalt().getName());
			}

		});
		amount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GristPart, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<GristPart, Number> p) {
				return new SimpleDoubleProperty(p.getValue().getAmount().value());
			}

		});

		fermentablesTable.getColumns().addAll(name, amount);
	}

	private void buildHopsTable() {
		TableColumn<HopAddition, String> name = new TableColumn<>("Name");
		TableColumn<HopAddition, Number> amount = new TableColumn<>("Amount");
		TableColumn<HopAddition, Number> timeInBoil = new TableColumn<>("Time in boil");

		name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HopAddition, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<HopAddition, String> p) {
				return new SimpleStringProperty(p.getValue().getHop().getName());
			}
		});
		amount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HopAddition, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<HopAddition, Number> p) {
				return new SimpleDoubleProperty(p.getValue().getAmount().value());
			}

		});
		timeInBoil.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HopAddition, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<HopAddition, Number> p) {
				return new SimpleDoubleProperty(p.getValue().getTimeInBoil().value());
			}

		});

		hopsTable.getColumns().addAll(name, amount, timeInBoil);
	}

	private void buildYeastsTable() {
		TableColumn<YeastAddition<?>, String> name = new TableColumn("Name");
		TableColumn<YeastAddition<?>, Number> amount = new TableColumn("Amount");

		name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<YeastAddition<?>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<YeastAddition<?>, String> p) {
				return new SimpleStringProperty(p.getValue().getYeast().getName());
			}

		});
		amount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<YeastAddition<?>, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<YeastAddition<?>, Number> p) {
				return new SimpleDoubleProperty(p.getValue().getAmount().value().doubleValue());
			}

		});

		yeastsTable.getColumns().addAll(name, amount);
	}

	public void setIngredientsList(IngredientsList ingredientsList) {

		//ObservableList<GristPart> gristParts = FXCollections.observableArrayList(new GristPart(new Malt("Münich", new Lovibond(1), new ExtractPotential(new GravityPoints(1), new Kilograms(1)), Malt.TYPE.GRAIN), new Kilograms(1)));
		ObservableList<GristPart> gristParts = FXCollections.observableArrayList(ingredientsList.getFermentables());
		ObservableList<HopAddition> hopAdditions = FXCollections.observableArrayList(ingredientsList.getHopAdditions());
		ObservableList<YeastAddition<?>> yeastAdditions = FXCollections.observableArrayList(ingredientsList.getYeastAdditions());

		fermentablesTable.setItems(gristParts);
		hopsTable.setItems(hopAdditions);
		yeastsTable.setItems(yeastAdditions);
	}

}

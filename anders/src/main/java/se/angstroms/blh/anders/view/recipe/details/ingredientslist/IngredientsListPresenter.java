package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.inject.Inject;
import org.blh.core.ingredient.Hop;
import org.blh.core.ingredient.Malt;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.Factor;
import org.blh.recipe.uncategorized.IngredientsList;
import se.angstroms.blh.anders.data.HopStore;
import se.angstroms.blh.anders.data.MaltStore;
import se.angstroms.blh.anders.view.util.table.ColumnPercentageWidthHelper;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Lists the specified ingredients
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class IngredientsListPresenter extends GridPane {

    @Inject private MaltStore maltStore;
    @Inject private HopStore hopStore;

    @FXML private TableView<GristPart> fermentablesTable;
    @FXML private TableColumn<GristPart, Malt> fermentablesName;
    @FXML private TableColumn<GristPart, String> fermentablesAmount;

    @FXML private TableView<HopAddition> hopsTable;
    @FXML private TableColumn<HopAddition, Hop> hopsName;
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

    private void buildFermentablesTable() {
        fermentablesName = IngredientsListColumnHelper.initialize(fermentablesName)
                .withRowToCellFunction((gristPart) -> gristPart.getMalt())
                .withCellToStringFunction((malt) -> malt.getName())
                .withValidValues(maltStore.getAll())
                .withOnRowEdited((row, newValue) -> {
                    System.out.println("Should update " + row + " to use " + newValue);
                    //row.setMalt(newValue);
                    //ingredientsListProperty.get().getFermentables().add(new GristPart(newValue, new Kilograms(1)));
                })
                .withPlaceholder(new Label("No malts matched the search, click here to add a malt"))
                .withOnNoMatchClick((searchText) -> System.out.println("Open new malt dialog. The name of the malt should be " + searchText))
                .withWidthFactor(new Factor(0.75))
                .commit();

        fermentablesAmount.setCellValueFactory(
                (TableColumn.CellDataFeatures<GristPart, String> p) -> new SimpleStringProperty(p.getValue().getAmount().toString())
        );

        fermentablesTable.setEditable(true);
        fermentablesTable.getVisibleLeafColumns().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                if (fermentablesTable.getVisibleLeafColumns().isEmpty()) {
                    // TODO: The checkbox before the entries in the menu button
                    // do not follow when controlling the visibility like this.
                    fermentablesTable.getColumns().get(0).setVisible(true);
                }
            }
        });

        ColumnPercentageWidthHelper.bind(new Factor(0.25), fermentablesAmount);
    }

    private void buildHopsTable() {

        hopsName = IngredientsListColumnHelper.initialize(hopsName)
                .withRowToCellFunction((hopAddition) -> hopAddition.getHop())
                .withCellToStringFunction((hop) -> hop.getName())
                .withValidValues(hopStore.getAll())
                .withOnRowEdited((row, newValue ) -> {})
                .withPlaceholder(new Label("No hops matches the serach, click here to add a new hop"))
                .withOnNoMatchClick((searchText) -> {})
                .withWidthFactor(new Factor(0.4))
                .commit();

        hopsAmount.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getAmount().value().toString())
        );
        hopsAlphaAcids.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getHop().getAlphaAcids().toString())
        );
        hopsTimeInBoil.setCellValueFactory(
                (TableColumn.CellDataFeatures<HopAddition, String> p) -> new SimpleStringProperty(p.getValue().getTimeInBoil().value().toString())
        );

        ColumnPercentageWidthHelper.bind(new Factor(0.2), hopsAmount);
        ColumnPercentageWidthHelper.bind(new Factor(0.2), hopsAlphaAcids);
        ColumnPercentageWidthHelper.bind(new Factor(0.2), hopsTimeInBoil);
    }

    private void buildYeastsTable() {
        yeastsName.setCellValueFactory(
                (TableColumn.CellDataFeatures<YeastAddition<?>, String> p) -> new SimpleStringProperty(p.getValue().getYeast().getName())
        );
        yeastsAmount.setCellValueFactory(
                (TableColumn.CellDataFeatures<YeastAddition<?>, String> p) -> new SimpleStringProperty(p.getValue().getAmount().toString())
        );

        ColumnPercentageWidthHelper.bind(new Factor(0.75), yeastsName);
        ColumnPercentageWidthHelper.bind(new Factor(0.25), yeastsAmount);
    }

	public ObjectProperty<IngredientsList> ingredientsListProperty() {
		return ingredientsListProperty;
	}

    private void setIngredientsList(IngredientsList ingredientsList) {
        // I need to copy the lists here so that sorting the tables won't cause
        // the formulas depending on the ingredients to be recalculated.

        fermentablesTable.setItems(FXCollections.observableList(ingredientsList.getFermentables()));
        hopsTable.setItems(FXCollections.observableList(ingredientsList.getHopAdditions()));
        yeastsTable.setItems(FXCollections.observableList(ingredientsList.getYeastAdditions()));
    }
}

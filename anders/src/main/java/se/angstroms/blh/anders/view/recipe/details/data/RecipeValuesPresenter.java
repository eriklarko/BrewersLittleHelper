package se.angstroms.blh.anders.view.recipe.details.data;


import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.view.recipe.details.data.value.ValuePresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thinner
 */
public class RecipeValuesPresenter extends VBox {

	private static enum GridElement {

		bitterness(Value.Id.BITTERNESS, 0, 0),
		og(Value.Id.OG, 1, 0),
		fg(Value.Id.FG, 2, 0),
		abv(Value.Id.ALCOHOL_CONTENT, 3, 0),
		extractionEfficency(Value.Id.EXTRACTION_EFFICIENCY, 0, 1),
		preFermentationVolume(Value.Id.POST_BOIL_VOLUME, 1, 1),
		preMashVolume(Value.Id.PRE_MASH_VOLUME, 2, 1);

		private final Value.Id type;
		private final int row;
		private final int column;

		private GridElement(Value.Id type, int row, int column) {
			this.type = type;
			this.row = row;
			this.column = column;
		}

		public Value.Id getType() {
			return type;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}
	}

	@FXML
	private GridPane grid;

	private final ObjectProperty<FullContext> recipeProperty;

	public RecipeValuesPresenter() {
		CustomControl.setup(this);
		recipeProperty = new SimpleObjectProperty<>();
		recipeProperty.addListener(new ChangeListener<FullContext>() {

			@Override
			public void changed(ObservableValue<? extends FullContext> ov, FullContext t, FullContext newRecipe) {
				populateGrid();
			}
		});
	}

	public ObjectProperty<FullContext> recipeProperty() {
		return recipeProperty;
	}

	private void populateGrid() {
		grid.getChildren().clear();
		int cellsPerElement = 4;

		for (GridElement element : GridElement.values()) {
			int column = element.getColumn() * cellsPerElement;
			ValuePresenter valuePresenter = typeToValuePresenter(element.getType());

			String title = element.type.getHumanReadable();
			if (element.getType() == Value.Id.PRE_MASH_VOLUME) {
				title = "Liters to warm";
			}
			Label valueTitle = new Label(title);
			valueTitle.getStyleClass().add("recipe-data-value-title");

			grid.add(valueTitle, column, element.getRow());
			grid.add(valuePresenter, column + 1, element.getRow());
			grid.add(valuePresenter.getGoBackToCalculatedButton(), column + 2, element.getRow());
			grid.add(valuePresenter.getExploreButton(), column + 3, element.getRow());
		}
	}

	private ValuePresenter typeToValuePresenter(Value.Id type) {
		Value<?> value = recipeProperty.get().get(type);
		InputtedOrCalculatedValue<?> valueAsIOCV = (InputtedOrCalculatedValue<?>) value;
		return new ValuePresenter(valueAsIOCV, type.getParser());
	}
}

package se.angstroms.blh.anders.view.recipe.details.data;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.angstroms.blh.anders.uncategorized.ValueId;
import se.angstroms.blh.anders.view.recipe.details.data.value.ValuePresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class RecipeValuesPresenter extends VBox {

	private static enum GridElement {
		bitterness("Bitterness", ValueId.BITTERNESS, 0 , 0),
		og("Original gravity", ValueId.OG, 1 , 0);

		private final String title;
		private final ValueId type;
		private final int row;
		private final int column;

		private GridElement(String title, ValueId type, int row, int column) {
			this.title = title;
			this.type = type;
			this.row = row;
			this.column = column;
		}

		public String getTitle() {
			return title;
		}

		public ValueId getType() {
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

    public RecipeValuesPresenter() {
        CustomControl.setup(this);

		populateGrid();
    }

	private void populateGrid() {
		for (GridElement element : GridElement.values()) {
			int column = element.getColumn() * 2;

			grid.add(new Label(element.title), column, element.getRow());
			grid.add(typeToValuePresenter(element.getType()), column * 2 + 1, element.getRow());
		}
	}

	private ValuePresenter typeToValuePresenter(ValueId type) {
		ValuePresenter.ValuePresenterBuilder valuePresenterBuilder = new ValuePresenter.ValuePresenterBuilder();
		valuePresenterBuilder.setType(type);
		return valuePresenterBuilder.build();
	}
}

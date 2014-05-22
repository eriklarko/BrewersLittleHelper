package se.angstroms.blh.anders.view.recipe.details.data;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.view.recipe.details.data.value.ValuePresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class RecipeValuesPresenter extends VBox {

    private static enum GridElement {

        bitterness("Bitterness", ValueId.BITTERNESS, 0, 0),
        og("Original gravity", ValueId.OG, 1, 0),
        extractionEfficency("Extraction efficiency", ValueId.EXTRACTION_EFFICIENCY, 0, 1);

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
        int cellsPerElement = 3;

        for (GridElement element : GridElement.values()) {
            int column = element.getColumn() * cellsPerElement;
            ValuePresenter valuePresenter = typeToValuePresenter(element.getType());

            grid.add(new Label(element.title), column, element.getRow());
            grid.add(valuePresenter, column + 1, element.getRow());
            grid.add(valuePresenter.getGoBackToCalculatedButton(), column + 2, element.getRow());
        }
    }

    private ValuePresenter typeToValuePresenter(ValueId type) {
        ValuePresenter.ValuePresenterBuilder valuePresenterBuilder = new ValuePresenter.ValuePresenterBuilder();
        valuePresenterBuilder.setType(type);
        return valuePresenterBuilder.build();
    }
}

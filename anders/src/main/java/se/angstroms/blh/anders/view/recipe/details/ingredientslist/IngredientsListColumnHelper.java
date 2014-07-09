package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.blh.core.unit.Factor;
import se.angstroms.blh.anders.view.util.table.ColumnPercentageWidthHelper;
import se.angstroms.blh.anders.view.util.table.ImmediateEditCellFactory;
import se.angstroms.blh.anders.view.util.table.ReasonableComboBoxCell;

/**
 *
 * Builder style helper class to create columns used to select ingredients in
 * the ingredients lists tables.
 *
 * Do not forget to invoke commit! :)
 *
 * @author eriklark
 */
public class IngredientsListColumnHelper<S, T> {

    public static <S, T> IngredientsListColumnHelper<S, T> initialize(TableColumn<S, T> column) {
        return new IngredientsListColumnHelper<>(column);
    }

    private final TableColumn<S, T> column;
    private Function<S, T> rowToCell;

    private Function<T, String> cellToString;
    private ObservableList<T> validValues;
    private Node placeholder;
    private Consumer<String> onNoMatchClick;
    private BiConsumer<S, T> onRowEdited;
    private Factor widthFactor;

    private IngredientsListColumnHelper(TableColumn<S, T> column) {
        this.column = column;
    }

    public IngredientsListColumnHelper<S, T> withRowToCellFunction(Function<S, T> rowToCell) {
        this.rowToCell = rowToCell;
        return this;
    }

    public IngredientsListColumnHelper<S, T> withCellToStringFunction(Function<T, String> cellToString) {
        this.cellToString = cellToString;
        return this;
    }

    public IngredientsListColumnHelper<S, T> withValidValues(ObservableList<T> validValues) {
        this.validValues = validValues;
        return this;
    }

    public IngredientsListColumnHelper<S, T> withPlaceholder(Node placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public IngredientsListColumnHelper<S, T> withOnNoMatchClick(Consumer<String> onNoMatchClick) {
        this.onNoMatchClick = onNoMatchClick;
        return this;
    }

    public IngredientsListColumnHelper<S, T> withOnRowEdited(BiConsumer<S, T> onNewValueSelected) {
        this.onRowEdited = onNewValueSelected;
        return this;
    }

    public IngredientsListColumnHelper<S, T> withWidthFactor(Factor widthFactor) {
        this.widthFactor = widthFactor;
        return this;
    }

    public TableColumn<S, T> commit() {
        column.setCellValueFactory(
                (TableColumn.CellDataFeatures<S, T> p) -> new SimpleObjectProperty<>(rowToCell.apply(p.getValue()))
        );

        placeholder.setOnMouseReleased((event) -> {
            Node intersectedNode = event.getPickResult().getIntersectedNode();
            Object comboBoxCandidate = intersectedNode.getParent().getProperties().get(ReasonableComboBoxCell.COMBOBOX_KEY);

            if (comboBoxCandidate instanceof ComboBox) {
                // TODO: The editor always returns an empty string. This is not god. I really hate that ComboBox implementation..
                String s = ((ComboBox) comboBoxCandidate).getEditor().getText();
                onNoMatchClick.accept(((ComboBox) comboBoxCandidate).getEditor().getText());
            } else {
                throw new IllegalArgumentException("The combo box parent wasn't a combobox!");
            }
        });

        Callback<TableColumn<S, T>, TableCell<S, T>> comboBoxCell = ReasonableComboBoxCell.forTableColumn(
                cellToString,
                validValues,
                placeholder
        );

        ImmediateEditCellFactory.attachTo(column).andForwardTo(comboBoxCell);

        column.setOnEditCommit((event) -> {
            // When I implement the functionality to add new rows to the tables,
            // here would be a good place to use another callback for new rows.

            // If the row was new
              // onNewRow.accept(event.getNewValue());
            // else
              onRowEdited.accept(/*event.getRowValue()*/null, event.getNewValue());
        });
        ColumnPercentageWidthHelper.bind(widthFactor, column);

        return column;
    }
}

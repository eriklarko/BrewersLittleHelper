package se.angstroms.blh.anders.view.util.table;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.blh.core.unit.Factor;

/**
 * Give columns percentage widths. Maintains column widths across table resizes.
 * Remembers if the user resizes the column. I.E. the specified percentage is
 * replaced when a column is resized.
 *
 * @author eriklark
 */
public class ColumnPercentageWidthHelper<S> {

    private double percentage;
    private final TableColumn<S, ?> column;
    private final TableView<S> table;

    public static <S> void bind(Factor percentage, TableColumn<S, ?> column) {
        new ColumnPercentageWidthHelper<>(percentage, column);
    }

    private ColumnPercentageWidthHelper(Factor percentage, TableColumn<S, ?> column) {
        this.percentage = percentage.value();
        this.column = column;
        this.table = column.getTableView();

        table.widthProperty().addListener(this::tableWidthChanged);
        column.widthProperty().addListener(this::columnWidthChanged);
    }

    private void tableWidthChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        column.setPrefWidth(table.getWidth() * percentage);
    }

    private void columnWidthChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (table.getWidth() > 0) {
            percentage = newValue.doubleValue() / table.getWidth();
        }
    }
}

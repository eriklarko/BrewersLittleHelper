package se.angstroms.blh.anders.view.util.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Enters the editable mode of the cell on the first click, not the THIRD as is
 * the default.
 * 
 * @author eriklark
 */
public class ImmediateEditCellFactory<S, T> extends ClickableCellFactory<S, T> {

    public static <S,T> Builder attachTo(TableColumn<S, T> tableColumn) {
        return new Builder<>(tableColumn);
    }

    private final TableView<S> table;

    private ImmediateEditCellFactory(TableColumn<S, T> tableColumn, Callback<TableColumn<S, T>, TableCell<S, T>> toWrap) {
        super(toWrap);
        this.table = tableColumn.getTableView();
        super.setOnMouseClicked(this::clickHandler);
    }

    private void clickHandler(MouseEvent event) {
        TableCell tc = (TableCell) event.getSource();
        int someIndex = tc.getIndex();
        if (someIndex < table.getItems().size()) {
            tc.startEdit();
        }
    }

    public static class Builder<S, T> {

        private final TableColumn<S, T> tableColumn;

        private Builder(TableColumn<S, T> tableColumn) {
            this.tableColumn = tableColumn;
        }

        public void andForwardTo(Callback<TableColumn<S, T>, TableCell<S, T>> toWrap) {
            ImmediateEditCellFactory<S, T> immediateEditCellFactory = new ImmediateEditCellFactory<>(tableColumn, toWrap);
            tableColumn.setCellFactory(immediateEditCellFactory);
        }
    }
}

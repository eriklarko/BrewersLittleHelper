package se.angstroms.blh.anders.view.util.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Allows to listen to double clicks on table cells
 * @author Thinner
 */
public class DoubleClickableCellFactory<S, T> extends ClickableCellFactory<S, T> {

    public static interface DoubleClickListener {

        void onDoubleClick(MouseEvent event);
    }

    private final DoubleClickListener doubleClickHandler;

    public DoubleClickableCellFactory(Callback<TableColumn<S, T>, TableCell<S, T>> toWrap, DoubleClickListener doubleClickHandler) {
        super(toWrap);
        this.doubleClickHandler = doubleClickHandler;
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> p) {
        TableCell<S, T> cell = super.call(p);
        attachMouseEventHandler(cell);

        return cell;
    }

    private void attachMouseEventHandler(TableCell<S, T> cell) {
        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (event.getClickCount() % 2 == 0) {
                doubleClickHandler.onDoubleClick(event);
            }
        });
    }
}

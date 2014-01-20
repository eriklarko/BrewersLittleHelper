package se.angstroms.blh.anders.view.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 *
 * @author Thinner
 */
public class DoubleClickableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    public static interface DoubleClickListener {

        void onDoubleClick(MouseEvent event);
    }

    private final Callback<TableColumn<S, T>, TableCell<S, T>> factory;
    private final DoubleClickListener doubleClickHandler;

    public DoubleClickableCellFactory(Callback<TableColumn<S, T>, TableCell<S, T>> factoryToWrap, DoubleClickListener doubleClickHandler) {
        this.factory = factoryToWrap;
        this.doubleClickHandler = doubleClickHandler;
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> p) {
        TableCell<S, T> cell = factory.call(p);
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

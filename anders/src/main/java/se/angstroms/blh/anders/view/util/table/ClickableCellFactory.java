package se.angstroms.blh.anders.view.util.table;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Used to attach a click listener to a table cell
 *
 * Stolen from http://monkeyhash.wordpress.com/2011/10/20/javafx-2-0-table-click-events/
 */
public class ClickableCellFactory<S, T> implements Callback<TableColumn<S,T>, TableCell<S,T>> {

    private EventHandler<? super MouseEvent> click;
    private final Callback<TableColumn<S, T>, TableCell<S, T>> toWrap;

    public ClickableCellFactory(Callback<TableColumn<S, T>, TableCell<S, T>> toWrap) {
        this.toWrap = toWrap;
    }

    public ClickableCellFactory(EventHandler<? super MouseEvent> click, Callback<TableColumn<S, T>, TableCell<S, T>> toWrap) {
        this(toWrap);
        this.click = click;
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> click) {
        this.click = click;
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> p) {
        TableCell<S, T> cell = toWrap.call(p);

       if(click != null) {
          cell.setOnMouseClicked(click);
       }

       return cell;
    }
}
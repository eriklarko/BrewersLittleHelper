package se.angstroms.blh.anders.view.util.table;

import se.angstroms.blh.anders.view.util.FilterableComboBox;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * I didn't like the default feel of the combobox cell, this class has other -
 * hopefully better defaults.
 *
 * @author eriklark
 */
public class ReasonableComboBoxCell<S, T> extends TableCell<S, T> {

    public static final String COMBOBOX_KEY = "parent";

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(ObservableList<T> ol) {
        return forTableColumn(null, ol);
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(Function<T, String> toString, ObservableList<T> ol) {
        return new Callback<TableColumn<S, T>, TableCell<S, T>>() {

            @Override
            public TableCell<S, T> call(TableColumn<S, T> p) {
                return new ReasonableComboBoxCell<>(toString, ol);
            }
        };
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(Function<T, String> toString, ObservableList<T> ol, Node placeholder) {
        return new Callback<TableColumn<S, T>, TableCell<S, T>>() {

            @Override
            public TableCell<S, T> call(TableColumn<S, T> p) {
                ReasonableComboBoxCell<S, T> comboBoxCell = new ReasonableComboBoxCell<>(toString, ol);
                comboBoxCell.comboBox.setPlaceholder(placeholder);
                placeholder.getProperties().put(COMBOBOX_KEY, comboBoxCell.comboBox);
                return comboBoxCell;
            }
        };
    }

    private final Function<T, String> toString;
    private final FilterableComboBox<T> comboBox;

    public ReasonableComboBoxCell(Function<T, String> toString, ObservableList<T> list) {
        this.toString = toString;
        this.comboBox = new FilterableComboBox(list, FilterableComboBox.STRING_CONTAINS(this::asString));

        setupComboBox();
    }

    private void setupComboBox() {
        this.comboBox.setItemToString(this::asString);

        this.comboBox.valueProperty().addListener((a, b, val) -> {
            if (val != null) {
                commitEdit(val);
            }
        });
        Platform.runLater(() -> this.comboBox.setValue(this.getItem()));
    }

    private String asString(T item) {
        if (item == null) {
            return "";
        } else if (toString == null) {
            return item.toString();
        } else {
            return toString.apply(item);
        }
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
            return;
        }

        super.startEdit();
        showComboBox();
    }

    private void showComboBox() {
        setText(null);
        setGraphic(comboBox);
        comboBox.show();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        showLabel();
    }

    private void showLabel() {
        setText(getItemText());
        setGraphic(null);
    }

    private String getItemText() {
        return (toString == null) ? getItem().toString() : toString.apply(getItem());
    }

    @Override
    protected void updateItem(T t, boolean empty) {
        super.updateItem(t, empty);
        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else if (isEditing()) {
            System.out.println("NÄR HÄNDER JAG???");
            showComboBox();
        } else {
            showLabel();
        }
    }
}

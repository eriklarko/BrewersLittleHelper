package se.angstroms.blh.anders.view.util;

import java.util.function.BiFunction;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

/**
 * TODO: Add different placeholder texts for when the original list is empty
 * and when the filter doesn't match anything.
 *
 * @author eriklark
 */
public class FilterableComboBox<T> extends ComboBox<T> {

    public static final <T> BiFunction<T, String, Boolean> STRING_CONTAINS(Function<T, String> itemToString) {
        return (T item, String text) -> itemToString.apply(item).toLowerCase().contains(text.toLowerCase());
    }

    private final ObservableList<T> fullList;
    private final BiFunction<T, String, Boolean> filter;

    public FilterableComboBox(ObservableList<T> ol, BiFunction<T, String, Boolean> filter) {
        super(ol);
        this.fullList = ol;
        this.filter = filter;

        this.getEditor().setOnKeyTyped((e) -> doFilter());
        this.setEditable(true);
    }

    private void doFilter() {
        Platform.runLater(() -> {
            FilteredList<T> itemsToShow = fullList.filtered((item) -> filter.apply(item, this.getEditor().getText()));
            setItems(itemsToShow);

            if (!this.isShowing()) {
                this.show();
            }
        });
    }

    /**
     * Do not use in conjuction with the default Converter...
     */
    public void setItemToString(Function<T, String> itemToString) {
        setConverter(new StringConverter<T>() {

            @Override
            public String toString(T object) {
                return itemToString.apply(object);
            }

            @Override
            public T fromString(String string) {
                System.out.println("fromString " + string + " => " + getSelectionModel().getSelectedItem());
                return getSelectionModel().getSelectedItem();
            }
        });
    }
}

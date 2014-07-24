package se.angstroms.blh.anders.view.util;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import se.angstroms.blh.anders.view.common.GridListView;

/**
 *
 * @author eriklark
 */
public class GenericBidirectionalBindings {

    // TODO: Test it :)
    public static <T, U> void bidirectionalBinding(Property<T> t, Property<U> u, Function<T, U> t2u, Function<U, T> u2t) {
        ThreadLocal<ChangeListener<U>> uListenerHack = new ThreadLocal<>();
        ChangeListener<T> onTchanged = (ObservableValue<? extends T> _1, T _2, T newT) -> Platform.runLater(() -> {
            u.removeListener(uListenerHack.get());
            u.setValue(t2u.apply(newT));
            u.addListener(uListenerHack.get());
        });
        ChangeListener<U> onUchanged = (ObservableValue<? extends U> _1, U _2, U newU) -> {
            t.removeListener(onTchanged);
            t.setValue(u2t.apply(newU));
            t.addListener(onTchanged);
        };
        uListenerHack.set(onUchanged);
        u.addListener(onUchanged);
        t.addListener(onTchanged);
    }

    public static <T extends Property<?>> Collection<GridListView.GridRow<T>> toGridRows(ObservableList<T> models, Function<T, GridListView.GridRow<T>> creator) {
        return models.stream().map((model) -> {
            GridListView.GridRow<T> gridRow = creator.apply(model);

            gridRow.getModel().addListener((source, _2, n) -> {
                T asProperty = (T) source;
                int index = models.indexOf(asProperty);
                models.set(index, asProperty);
            });
            return gridRow;
        }).collect(Collectors.toList());
    }

    private GenericBidirectionalBindings(){
    }
}

package se.angstroms.blh.anders.view.util;

import java.util.function.Function;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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

    private GenericBidirectionalBindings(){
    }
}

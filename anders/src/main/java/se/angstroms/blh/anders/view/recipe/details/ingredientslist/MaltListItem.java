package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import java.util.Arrays;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;
import org.blh.core.ingredient.Malt;
import org.blh.core.recipe.GristPart;
import org.blh.core.unit.weight.Kilograms;
import se.angstroms.blh.anders.data.MaltStore;

public class MaltListItem implements GridListView.GridRow {

    static int i = 10;

    public static Node test() {
        MaltListItem mli;

        GridListView lv = new GridListView(FXCollections.observableArrayList(
                mli = new MaltListItem(new GristPart(new Malt("asd", null, null, Malt.TYPE.GRAIN), new Kilograms(1)), new MaltStore()),
                new MaltListItem(new GristPart(new Malt("asd", null, null, Malt.TYPE.GRAIN), new Kilograms(1)), new MaltStore()),
                new MaltListItem(new GristPart(new Malt("asd", null, null, Malt.TYPE.GRAIN), new Kilograms(1)), new MaltStore()),
                new MaltListItem(new GristPart(new Malt("asd", null, null, Malt.TYPE.GRAIN), new Kilograms(1)), new MaltStore()
                )));

        //new Timer(2000, (e) -> mli.modelProperty().set(new GristPart(new Malt("BLÖÖÖÖÖ " + i++, null, null, Malt.TYPE.GRAIN), new Kilograms(i)))).start();
        return lv;
    }

    // TODO: Make available to other classes. And test it :)
    private static <T, U> void bidirectionalBinding(Property<T> t, Property<U> u, Function<T, U> t2u, Function<U, T> u2t) {
        ThreadLocal<ChangeListener<U>> uListenerHack = new ThreadLocal<>();
        ChangeListener<T> tListener = (_1, _2, newValue) -> Platform.runLater(() -> {
            u.removeListener(uListenerHack.get());
            u.setValue(t2u.apply(newValue));
            u.addListener(uListenerHack.get());
        });
        ChangeListener<U> uListener = (_1, _2, newValue) -> {
            t.removeListener(tListener);
            t.setValue(u2t.apply(newValue));
            t.addListener(tListener);
        };
        uListenerHack.set(uListener);

        u.addListener(uListener);
        t.addListener(tListener);
    }

    private final MaltStore ms;
    private final ObjectProperty<GristPart> model;

    public MaltListItem(GristPart model, MaltStore ms) {
        this.model = new SimpleObjectProperty<>(model);
        this.ms = ms;
    }

    public ObjectProperty<GristPart> modelProperty() {
        return model;
    }

    private Node namePart() {
        SelectBoxLabel<Malt> selectBoxLabel = new SelectBoxLabel<>(model.get().getMalt(), ms.getAll(), (malt) -> malt.getName());
        Function<GristPart, Malt> toMalt = (gp) -> gp.getMalt();
        Function<Malt, GristPart> fromMalt = (malt) -> new GristPart(malt, model.get().getAmount());
        bidirectionalBinding(model, selectBoxLabel.modelProperty(), toMalt, fromMalt);

        return selectBoxLabel;
    }

    private Node amountPart() {
        ListSpinner<Integer> spinner = new ListSpinner<>(0, Integer.MAX_VALUE - 1);
        Function<GristPart, Integer> gp2int = (gp) -> gp.getAmount().value().intValue();
        Function<Integer, GristPart> int2gp = (amount) -> new GristPart(model.get().getMalt(), new Kilograms(amount));
        bidirectionalBinding(model, spinner.valueProperty(), gp2int, int2gp);

        return spinner;
    }

    @Override
    public Iterable<Node> getNodes() {
        return Arrays.asList(
                namePart(),
                amountPart()
        );
    }
}

package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;
import org.blh.core.ingredient.Malt;
import org.blh.core.recipe.GristPart;
import org.blh.core.unit.weight.Kilograms;
import org.blh.recipe.uncategorized.ObservableGristPart;
import se.angstroms.blh.anders.data.MaltStore;
import se.angstroms.blh.anders.view.util.GenericBidirectionalBindings;
import se.angstroms.blh.anders.view.util.listspinner.ListSpinnerDoubleList;
import se.angstroms.blh.anders.view.util.listspinner.WidthAndValueKeepingStringConverter;

public class MaltListItem implements GridListView.GridRow<ObservableGristPart> {

    public static Collection<GridListView.GridRow<ObservableGristPart>> toGridRows(ObservableList<ObservableGristPart> gps, MaltStore maltStore) {
        return GenericBidirectionalBindings.toGridRows(gps, (model) -> new MaltListItem(model, maltStore));
    }

    private final MaltStore ms;
    private final ObservableGristPart model;

    public MaltListItem(ObservableGristPart model, MaltStore ms) {
        this.model = model;
        this.ms = ms;
    }

    public ObjectProperty<GristPart> modelProperty() {
        return model;
    }

    private Node namePart() {
        SelectBoxLabel<Malt> selectBoxLabel = new SelectBoxLabel<>(model.get().getMalt(), ms.getAll(), (malt) -> malt.getName());
        Function<GristPart, Malt> toMalt = (gp) -> gp.getMalt();
        Function<Malt, GristPart> fromMalt = (malt) -> new GristPart(malt, model.get().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, selectBoxLabel.modelProperty(), toMalt, fromMalt);

        return selectBoxLabel;
    }

    private Node amountPart() {
        ListSpinner<Double> spinner = new ListSpinner<>(new ListSpinnerDoubleList(0, 10000000, 0.1));
        spinner.setValue(model.get().getAmount().value());
        spinner.setEditable(true);
        spinner.setStringConverter(new WidthAndValueKeepingStringConverter(spinner));

        Function<GristPart, Double> toInt = (gp) -> gp.getAmount().value();
        Function<Double, GristPart> fromInt = (amount) -> new GristPart(model.get().getMalt(), new Kilograms(amount));
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }

    @Override
    public Iterable<Node> getNodes() {
        return Arrays.asList(
                namePart(),
                amountPart()
        );
    }

    @Override
    public ObservableGristPart getModel() {
        return model;
    }
}

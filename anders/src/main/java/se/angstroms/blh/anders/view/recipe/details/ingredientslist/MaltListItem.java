package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import se.angstroms.blh.anders.view.util.listspinner.ListSpinners;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.Property;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;
import org.blh.core.ingredient.Malt;
import org.blh.core.recipe.GristPart;
import org.blh.core.unit.weight.Kilograms;
import se.angstroms.blh.anders.data.Store;
import se.angstroms.blh.anders.view.common.RemoveButton;
import se.angstroms.blh.anders.view.util.GenericBidirectionalBindings;

class MaltListItem implements GridListView.GridRow<Property<GristPart>> {

    private final Store<Malt> ms;
    private final Property<GristPart> model;
    private final Consumer<Property<GristPart>> onDelete;

    public MaltListItem(Property<GristPart> model, Store<Malt> ms, Consumer<Property<GristPart>> onDelete) {
        this.model = model;
        this.ms = ms;
        this.onDelete = onDelete;
    }

    public Property<GristPart> modelProperty() {
        return model;
    }

    @Override
    public Property<GristPart> getModel() {
        return model;
    }

    @Override
    public Iterable<Node> getNodes() {
        return Arrays.asList(
                namePart(),
                amountPart(),
                removeButton()
        );
    }

    private Node namePart() {
        SelectBoxLabel<Malt> selectBoxLabel = new SelectBoxLabel<>(model.getValue().getMalt(), ms.getAll(), (malt) -> malt.getName());
        Function<GristPart, Malt> toMalt = (gp) -> gp.getMalt();
        Function<Malt, GristPart> fromMalt = (malt) -> new GristPart(malt, model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, selectBoxLabel.modelProperty(), toMalt, fromMalt);

        return selectBoxLabel;
    }

    private Node amountPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(model.getValue().getAmount().value());

        Function<GristPart, Double> toDouble = (gp) -> gp.getAmount().value();
        Function<Double, GristPart> fromDouble = (amount) -> new GristPart(model.getValue().getMalt(), new Kilograms(amount));
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toDouble, fromDouble);

        return spinner;
    }

    private Node removeButton() {
        return new RemoveButton(this, onDelete);
    }
}

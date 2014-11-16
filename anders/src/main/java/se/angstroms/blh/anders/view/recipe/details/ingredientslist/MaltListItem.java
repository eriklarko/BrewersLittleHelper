package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import se.angstroms.blh.anders.view.util.listspinner.ListSpinners;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.util.Pair;
import jfxtras.scene.control.ListSpinner;
import org.blh.core.ingredient.Malt;
import org.blh.core.recipe.GristPart;
import org.blh.core.unit.weight.Kilograms;
import se.angstroms.blh.anders.data.Store;
import se.angstroms.blh.anders.view.common.RemoveButton;
import se.angstroms.blh.anders.view.util.GenericBidirectionalBindings;
import static se.angstroms.blh.anders.view.common.GridListView.percentageWidth;

class MaltListItem implements GridListView.GridRow<GristPart> {

    private final Store<Malt> ms;
    private final Property<GristPart> model;
    private final Consumer<GristPart> onDelete;

    public MaltListItem(GristPart model, Store<Malt> ms, Consumer<GristPart> onDelete) {
        this.model = new SimpleObjectProperty<>(model);
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
    public Iterable<Pair<ColumnConstraints, Node>> getNodes() {
        return Arrays.asList(
                new Pair(/*percentageWidth(73)*/ null, namePart()),
                new Pair(null, amountPart()),
                new Pair(null, removeButton())
        );
    }

    private Node namePart() {
        SelectBoxLabel<Malt> selectBoxLabel = new SelectBoxLabel<>(model.getValue().getMalt(), ms.getAll(), (malt) -> malt.getName());
        Function<GristPart, Malt> toMalt = (gp) -> gp.getMalt();
        Function<Malt, GristPart> fromMalt = (malt) -> new GristPart(malt, model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, selectBoxLabel.modelProperty(), toMalt, fromMalt);

        selectBoxLabel.setAlignment(Pos.CENTER_LEFT);
        selectBoxLabel.setPadding(new Insets(0,0,0,10));

        return selectBoxLabel;
    }

    private Node amountPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(0, 100000, 0.01, model.getValue().getAmount().value());
        spinner.setPostfix(" kg");
        spinner.setStyle("-fx-background-radius: 0");

        Function<GristPart, Double> toDouble = (gp) -> gp.getAmount().value();
        Function<Double, GristPart> fromDouble = (amount) -> new GristPart(model.getValue().getMalt(), new Kilograms(amount));
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toDouble, fromDouble);

        return spinner;
    }

    private Node removeButton() {
        return new RemoveButton(this, onDelete);
    }
}

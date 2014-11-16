package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import se.angstroms.blh.anders.view.util.listspinner.ListSpinners;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.util.Pair;
import jfxtras.scene.control.ListSpinner;
import org.blh.core.ingredient.Yeast;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.Unit;
import org.blh.core.unit.volume.Milliliters;
import se.angstroms.blh.anders.data.YeastStore;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.RemoveButton;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import se.angstroms.blh.anders.view.util.GenericBidirectionalBindings;

/**
 *
 * @author eriklark
 */
class YeastListItem implements GridListView.GridRow<YeastAddition<?>> {

    private final Property<YeastAddition<?>> model;
    private final YeastStore yeastStore;
    private final Consumer<YeastAddition<?>> onDelete;

    public YeastListItem(YeastAddition<?> model, YeastStore yeastStore, Consumer<YeastAddition<?>> onDelete) {
        this.model = new SimpleObjectProperty<>(model);
        this.yeastStore = yeastStore;
        this.onDelete = onDelete;
    }

    @Override
    public Property<YeastAddition<?>> getModel() {
        return model;
    }

    @Override
    public Iterable<Pair<ColumnConstraints, Node>> getNodes() {
        return Arrays.asList(
                new Pair(null, getNamePart()),
                new Pair(null, getAmountPart()),
                new Pair(null, removeButton())
        );
    }

    private Node getNamePart() {
        SelectBoxLabel<Yeast> selectBoxLabel = new SelectBoxLabel<>(model.getValue().getYeast(), yeastStore.getAll(), (yeast) -> yeast.getName());
        Function<YeastAddition<?>, Yeast> toMalt = (ya) -> ya.getYeast();

        Function<Yeast, YeastAddition<?>> fromMalt = (yeast) -> new YeastAddition<Unit<? extends Number>>(yeast, model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, selectBoxLabel.modelProperty(), toMalt, fromMalt);

        selectBoxLabel.setAlignment(Pos.CENTER_LEFT);
        selectBoxLabel.setPadding(new Insets(0,0,0,10));

        return selectBoxLabel;
    }

    private Node getAmountPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(model.getValue().getAmount().value().doubleValue());
        spinner.setPostfix(" ml");
        spinner.setStyle("-fx-background-radius: 0");

        Function<YeastAddition<?>, Double> toInt = (ya) -> ya.getAmount().value().doubleValue();
        // TODO: This is not bound to be milliliters....
        Function<Double, YeastAddition<?>> fromInt = (amount) -> new YeastAddition<Unit<? extends Number>>(model.getValue().getYeast(), new Milliliters(amount));
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }

    private Node removeButton() {
        return new RemoveButton(this, onDelete);
    }
}

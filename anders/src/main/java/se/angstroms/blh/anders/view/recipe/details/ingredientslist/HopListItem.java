package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import java.util.function.Consumer;
import java.util.function.Function;

import org.blh.core.ingredient.Hop;
import org.blh.core.recipe.HopAddition;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.weight.Grams;

import se.angstroms.blh.anders.data.Store;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.RemoveButton;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import se.angstroms.blh.anders.view.util.GenericBidirectionalBindings;
import se.angstroms.blh.anders.view.util.listspinner.IntStringConverter;
import se.angstroms.blh.anders.view.util.listspinner.ListSpinners;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;

/**
 *
 * @author eriklark
 */
class HopListItem implements GridListView.GridRow<HopAddition> {

    private final Property<HopAddition> model;
    private final Store<Hop> hopStore;
    private final Consumer<HopAddition> onDelete;

    public HopListItem(HopAddition model, Store<Hop> hopStore, Consumer<HopAddition> onDelete) {
        this.model = new SimpleObjectProperty<>(model);
        this.hopStore = hopStore;
        this.onDelete = onDelete;
    }

    @Override
    public Property<HopAddition> getModel() {
        return model;
    }

    @Override
    public GridListView.GridRowColumnBuilder getColumns() {
        return new GridListView.GridRowColumnBuilder()
                .addColumn(getNamePart())
                .addColumn(getAmountPart())
                .addColumn(getAlphaAcidsPart())
                .addColumn(getTimeInBoilPart())
                .addColumn(removeButton());
    }

    private Node getNamePart() {
        SelectBoxLabel<Hop> sbl = new SelectBoxLabel<>(model.getValue().getHop(), hopStore.getAll(), (hop) -> hop.getName());
        Function<HopAddition, Hop> toHop = (hopAddition) -> hopAddition.getHop();
        Function<Hop, HopAddition> fromHop = (hop) -> new HopAddition(hop, model.getValue().getTimeInBoil(), model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, sbl.modelProperty(), toHop, fromHop);

        sbl.setAlignment(Pos.CENTER_LEFT);
        sbl.setPadding(new Insets(0,0,0,10));

        return sbl;
    }

    private Node getAmountPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(model.getValue().getAmount().value());
        spinner.setPostfix(" g");
        spinner.setStyle("-fx-background-radius: 0");

        Function<HopAddition, Double> toInt = (ha) -> ha.getAmount().value();
        Function<Double, HopAddition> fromInt = (amount) -> new HopAddition(model.getValue().getHop(), model.getValue().getTimeInBoil(), new Grams(amount));
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }

    private Node getAlphaAcidsPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(model.getValue().getHop().getAlphaAcids().value());
        spinner.setPostfix(" %");
        spinner.setStyle("-fx-background-radius: 0");

        Function<HopAddition, Double> toInt = (ha) -> ha.getHop().getAlphaAcids().value();
        Function<Double, HopAddition> fromInt = (amount) -> {
            Hop hop = new Hop(model.getValue().getHop().getName(), new Percentage(amount));
            return new HopAddition(hop, model.getValue().getTimeInBoil(), model.getValue().getAmount());
        };
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }

    private Node getTimeInBoilPart() {
        ListSpinner<Integer> spinner = new ListSpinner<>(0, 100000);
        spinner.setValue(model.getValue().getTimeInBoil().value().intValue());
        spinner.setEditable(true);
        spinner.setStringConverter(new IntStringConverter(spinner));
        spinner.setPostfix(" min");
        spinner.setStyle("-fx-background-radius: 0");

        Function<HopAddition, Integer> toInt = (ha) -> ha.getTimeInBoil().value().intValue();
        Function<Integer, HopAddition> fromInt = (amount) -> new HopAddition(model.getValue().getHop(), new Minutes(amount), model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }

    private Node removeButton() {
        return new RemoveButton(this, onDelete);
    }
}

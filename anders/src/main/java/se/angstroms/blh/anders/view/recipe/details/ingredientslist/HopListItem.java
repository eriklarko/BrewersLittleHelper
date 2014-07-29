package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import se.angstroms.blh.anders.view.util.listspinner.ListSpinners;
import java.util.Arrays;
import java.util.function.Function;
import javafx.beans.property.Property;
import javafx.scene.Node;
import jfxtras.scene.control.ListSpinner;
import org.blh.core.ingredient.Hop;
import org.blh.core.recipe.HopAddition;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.weight.Grams;
import se.angstroms.blh.anders.data.HopStore;
import se.angstroms.blh.anders.data.Store;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.SelectBoxLabel;
import se.angstroms.blh.anders.view.util.GenericBidirectionalBindings;
import se.angstroms.blh.anders.view.util.listspinner.IntStringConverter;

/**
 *
 * @author eriklark
 */
class HopListItem implements GridListView.GridRow<Property<HopAddition>> {

    private final Property<HopAddition> model;
    private final Store<Hop> hopStore;

    public HopListItem(Property<HopAddition> model, HopStore hopStore) {
        this.model = model;
        this.hopStore = hopStore;
    }

    @Override
    public Property<HopAddition> getModel() {
        return model;
    }

    @Override
    public Iterable<Node> getNodes() {
        return Arrays.asList(
                getNamePart(),
                getAmountPart(),
                getAlphaAcidsPart(),
                getTimeInBoilPart()
        );
    }

    private Node getNamePart() {
        SelectBoxLabel<Hop> sbl = new SelectBoxLabel<>(model.getValue().getHop(), hopStore.getAll(), (hop) -> hop.getName());
        Function<HopAddition, Hop> toHop = (hopAddition) -> hopAddition.getHop();
        Function<Hop, HopAddition> fromHop = (hop) -> new HopAddition(hop, model.getValue().getTimeInBoil(), model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, sbl.modelProperty(), toHop, fromHop);
        return sbl;
    }

    private Node getAmountPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(model.getValue().getAmount().value());

        Function<HopAddition, Double> toInt = (ha) -> ha.getAmount().value();
        Function<Double, HopAddition> fromInt = (amount) -> new HopAddition(model.getValue().getHop(), model.getValue().getTimeInBoil(), new Grams(amount));
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }

    private Node getAlphaAcidsPart() {
        ListSpinner<Double> spinner = ListSpinners.generic(model.getValue().getHop().getAlphaAcids().value());

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

        Function<HopAddition, Integer> toInt = (ha) -> ha.getTimeInBoil().value().intValue();
        Function<Integer, HopAddition> fromInt = (amount) -> new HopAddition(model.getValue().getHop(), new Minutes(amount), model.getValue().getAmount());
        GenericBidirectionalBindings.bidirectionalBinding(model, spinner.valueProperty(), toInt, fromInt);

        return spinner;
    }
}

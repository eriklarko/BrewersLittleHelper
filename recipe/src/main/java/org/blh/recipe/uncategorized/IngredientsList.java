package org.blh.recipe.uncategorized;

import java.util.Collection;
import java.util.stream.Collectors;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;

/**
 * Holds the ingredients of a recipe :) Loltroll self documenting... wait. no..
 * stfu.
 *
 * @author thinner
 */
public class IngredientsList {

    private final ObservableList<Property<GristPart>> fermentables;
    private final ObservableList<Property<HopAddition>> hopAdditions;
    private final ObservableList<Property<YeastAddition<?>>> yeastAdditions;

    public IngredientsList() {
        this(FXCollections.observableArrayList(), FXCollections.observableArrayList(), FXCollections.observableArrayList());
    }

    public IngredientsList(Collection<Property<GristPart>> fermentables,
            Collection<Property<HopAddition>> hopAdditions,
            Collection<Property<YeastAddition<?>>> yeastAdditions) {
        this(FXCollections.observableArrayList(fermentables),
                FXCollections.observableArrayList(hopAdditions),
                FXCollections.observableArrayList(yeastAdditions));
    }

    public IngredientsList(ObservableList<Property<GristPart>> fermentables,
            ObservableList<Property<HopAddition>> hopAdditions,
            ObservableList<Property<YeastAddition<?>>> yeastAdditions) {
        this.fermentables = fermentables;
        this.hopAdditions = hopAdditions;
        this.yeastAdditions = yeastAdditions;
    }

    public ObservableList<Property<GristPart>> getFermentables() {
        return fermentables;
    }

    public Collection<GristPart> getFermentablesSnapshot() {
        return getSnapshot(fermentables);
    }

    public void setFermentables(Collection<Property<GristPart>> fermentables) {
        this.fermentables.clear();
        this.fermentables.addAll(fermentables);
    }

    public ObservableList<Property<HopAddition>> getHopAdditions() {
        return hopAdditions;
    }

    public Collection<HopAddition> getHopAdditionsSnapshot() {
        return getSnapshot(hopAdditions);
    }

    private <T> Collection<T> getSnapshot(ObservableList<Property<T>> cs) {
        return cs.stream().map((c) -> c.getValue()).collect(Collectors.toList());
    }

    public void setHopAdditions(Collection<Property<HopAddition>> hopAdditions) {
        this.hopAdditions.clear();
        this.hopAdditions.addAll(hopAdditions);
    }

    public ObservableList<Property<YeastAddition<?>>> getYeastAdditions() {
        return yeastAdditions;
    }

    public Collection<YeastAddition<?>> getYeastAdditionsSnapshot() {
        return getSnapshot(yeastAdditions);
    }

    public void setYeastAdditions(Collection<Property<YeastAddition<?>>> yeastAddtitions) {
        this.yeastAdditions.clear();
        this.yeastAdditions.addAll(yeastAddtitions);
    }
}

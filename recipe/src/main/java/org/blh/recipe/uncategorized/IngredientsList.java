package org.blh.recipe.uncategorized;

import java.util.Collection;
import java.util.stream.Collectors;
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

    private final ObservableList<ObservableGristPart> fermentables;
    private final ObservableList<HopAddition> hopAdditions;
    private final ObservableList<YeastAddition<?>> yeastAdditions;

    public IngredientsList() {
        this(FXCollections.observableArrayList(), FXCollections.observableArrayList(), FXCollections.observableArrayList());
    }

    public IngredientsList(Collection<ObservableGristPart> fermentables,
            Collection<HopAddition> hopAdditions,
            Collection<YeastAddition<?>> yeastAdditions) {
        this(FXCollections.observableArrayList(fermentables),
                FXCollections.observableArrayList(hopAdditions),
                FXCollections.observableArrayList(yeastAdditions));
    }

    public IngredientsList(ObservableList<ObservableGristPart> fermentables,
            ObservableList<HopAddition> hopAdditions,
            ObservableList<YeastAddition<?>> yeastAdditions) {
        this.fermentables = fermentables;
        this.hopAdditions = hopAdditions;
        this.yeastAdditions = yeastAdditions;
    }

    public ObservableList<ObservableGristPart> getFermentables() {
        return fermentables;
    }

    public Collection<GristPart> getFermentablesSnapshot() {
        return fermentables.stream().map((ObservableGristPart ogp) -> ogp.get()).collect(Collectors.toList());
    }

    public void setFermentables(Collection<ObservableGristPart> fermentables) {
        this.fermentables.clear();
        this.fermentables.addAll(fermentables);
    }

    public ObservableList<HopAddition> getHopAdditions() {
        return hopAdditions;
    }

    public void setHopAdditions(Collection<HopAddition> hopAdditions) {
        this.hopAdditions.clear();
        this.hopAdditions.addAll(hopAdditions);
    }

    public ObservableList<YeastAddition<?>> getYeastAdditions() {
        return yeastAdditions;
    }

    public void setYeastAdditions(Collection<YeastAddition<?>> yeastAddtitions) {
        this.yeastAdditions.clear();
        this.yeastAdditions.addAll(yeastAddtitions);
    }
}

package org.blh.recipe.uncategorized;

import java.util.Collection;

import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Holds the ingredients of a recipe :) Loltroll self documenting... wait. no..
 * stfu.
 *
 * @author thinner
 */
public class IngredientsList {

    private final ObservableList<GristPart> fermentables;
    private final ObservableList<HopAddition> hopAdditions;
    private final ObservableList<YeastAddition<?>> yeastAdditions;

    public IngredientsList() {
        this.fermentables = FXCollections.observableArrayList();
        this.hopAdditions = FXCollections.observableArrayList();
        this.yeastAdditions = FXCollections.observableArrayList();
    }

    public ObservableList<GristPart> getFermentables() {
        return fermentables;
    }

    public void setFermentables(Collection<GristPart> fermentables) {
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

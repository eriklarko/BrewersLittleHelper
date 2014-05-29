package org.blh.recipe.uncategorized;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.gravity.GravityPoints;
import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.blh.core.unit.weight.Lbs;

/**
 * TODO: It seems that a lot of things here should be observable... All the
 * calculating methods should be observable imo...
 *
 * @author thinner
 */
public class IngredientsList {

    private final ObservableList<GristPart> fermentables;
    private final ObservableList<HopAddition> hopAdditions;
    private final ObservableList<YeastAddition<?>> yeastAdditions;

    public IngredientsList() {
        this(FXCollections.observableArrayList(), FXCollections.observableArrayList(), FXCollections.observableArrayList());
    }

    public IngredientsList(Collection<GristPart> fermentables,
            Collection<HopAddition> hopAdditions,
            Collection<YeastAddition<?>> yeastAdditions) {
        this(FXCollections.observableArrayList(fermentables),
                FXCollections.observableArrayList(hopAdditions),
                FXCollections.observableArrayList(yeastAdditions));
    }

    public IngredientsList(ObservableList<GristPart> fermentables,
            ObservableList<HopAddition> hopAdditions,
            ObservableList<YeastAddition<?>> yeastAdditions) {
        this.fermentables = fermentables;
        this.hopAdditions = hopAdditions;
        this.yeastAdditions = yeastAdditions;
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

    public Kilograms getTotalGrainWeight() {
        double totalWeight = 0;
        for (GristPart fermentable : fermentables) {
            totalWeight += fermentable.getMalt().getColor().value();
        }
        return new Kilograms(totalWeight);
    }

    public GravityPoints getTotalGravityPoints() {
        double specificGravityPoints = 0;
        for (GristPart fermentable : fermentables) {
            specificGravityPoints += fermentable.getMalt().getExtractPotential().value();
        }

        return new GravityPoints(specificGravityPoints);
    }

    public ColorPotential getTotalColorPotential() {
        ColorPotential potential = new ColorPotential();
        for (GristPart fermentable : fermentables) {
            potential.add(fermentable.getMalt().getColor(), new Lbs(fermentable.getAmount()));
        }
        return potential;
    }

    public Grams getTotalHopWeight() {
        double totalGrams = 0;
        for (HopAddition addition : hopAdditions) {
            totalGrams += addition.getAmount().value();
        }
        return new Grams(totalGrams);
    }
}

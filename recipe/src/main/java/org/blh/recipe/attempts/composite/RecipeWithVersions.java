package org.blh.recipe.attempts.composite;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.blh.core.uncategorized.BeerType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.InstructionsList;

/**
 * Describes a versions aware recipe. For compatibility or ease of use, the
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class RecipeWithVersions implements Recipe {

    private final String name;
    private final ObservableList<BasicRecipe> recipes;

    public RecipeWithVersions(BasicRecipe firstRecipe, BasicRecipe... recipes) {
        this.recipes = FXCollections.observableList(buildVersionsList(firstRecipe, recipes));
        this.name = getFirstRecipe().getName();
    }

    private List<BasicRecipe> buildVersionsList(BasicRecipe firstRecipe, BasicRecipe... recipesArray) {
        if (firstRecipe == null) {
            throw new NullPointerException("The first recipe must not be null");
        }

        List<BasicRecipe> recipes = new LinkedList<>();
        recipes.add(firstRecipe);
        recipes.addAll(Arrays.asList(recipesArray));
        return recipes;
    }

    private BasicRecipe getFirstRecipe() {
        return recipes.get(0);
    }

    public ObservableList<BasicRecipe> getRecipes() {
        return recipes;
    }

	@Override
    public String getName() {
        return name;
    }

    @Override
    public BeerType getType() {
        return getFirstRecipe().getType();
    }

    @Override
    public IngredientsList getIngredientsList() {
        return getFirstRecipe().getIngredientsList();
    }

    @Override
    public InstructionsList getInstructionsList() {
        return getFirstRecipe().getInstructionsList();
    }
}

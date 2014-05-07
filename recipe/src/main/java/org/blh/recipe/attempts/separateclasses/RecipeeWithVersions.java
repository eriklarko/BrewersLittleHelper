package org.blh.recipe.attempts.separateclasses;

import org.blh.core.uncategorized.BeerType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.InstructionsList;

/**
 * Recognizes that building recipes is an ongoing process.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class RecipeeWithVersions {

    private final String name;
    private final ObservableList<RecipeVersion> versions;

    public RecipeeWithVersions(ObservableList<Recipee> recipeVersions) {
        if (recipeVersions.isEmpty() || recipeVersions.get(0) == null) {
            throw new IllegalArgumentException("You provided an empty list of versions or the first version was null");
        }

        this.versions = validateAndDecorateInput(recipeVersions);
        this.name = recipeVersions.get(0).getName();
    }

    private ObservableList<RecipeVersion> validateAndDecorateInput(Iterable<Recipee> recipes) throws IllegalArgumentException {
        ObservableList<RecipeVersion> verisons = FXCollections.emptyObservableList();
        String previousName = null;
        for (Recipee version : recipes) {
            if (previousName != null && !version.getName().equals(previousName)) {
                throw new IllegalArgumentException("Not all versions have the same name");
            }

            previousName = version.getName();
            verisons.add(new RecipeVersion(version));
        }
        return verisons;
    }

    public String getName() {
        return name;
    }

    public ObservableList<RecipeVersion> getVersions() {
        return versions;
    }

    /**
     * Hides fields in the recipe class that are not applicable to recipe
     * versions
     */
    public static class RecipeVersion {

        private final Recipee toDecorate;

        public RecipeVersion(Recipee toDecorate) {
            this.toDecorate = toDecorate;
        }

        public BeerType getType() {
            return toDecorate.getType();
        }

        public IngredientsList getIngredientsList() {
            return toDecorate.getIngredientsList();
        }

        public InstructionsList getInstructionsList() {
            return toDecorate.getInstructionsList();
        }
    }
}

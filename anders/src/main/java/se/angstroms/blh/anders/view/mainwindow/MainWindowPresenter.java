package se.angstroms.blh.anders.view.mainwindow;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.blh.core.ingredient.Hop;
import org.blh.core.ingredient.Malt;
import org.blh.core.ingredient.Yeast;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.uncategorized.BeerType;
import org.blh.core.unit.ExtractPotential;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.gravity.GravityPoints;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.blh.recipe.uncategorized.IngredientsList;
import org.blh.recipe.uncategorized.Recipe;

import se.angstroms.blh.anders.view.recipe.details.RecipeDetailsPresenter;
import se.angstroms.blh.anders.view.recipe.list.RecipeListPresenter;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * The main window.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class MainWindowPresenter implements Initializable {

    @FXML
    private Pane recipesContainer;

    private RecipeListPresenter recipeList;
    private RecipeDetailsPresenter recipeDetails;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipeList = new RecipeListPresenter();
        recipeDetails = new RecipeDetailsPresenter();

        recipeList.getSelectedRecipeProperty().addListener((ObservableValue<? extends Recipe> ov, Recipe oldValue, Recipe newValue) -> {
            recipeDetails.setRecipe(newValue);
            showRecipeDetails();
        });

        recipeList.setRecipes(getDummyRecipeList());
        showRecipeList();
    }

    private void showRecipeList() {
        recipesContainer.getChildren().clear();
        recipesContainer.getChildren().add(recipeList);
    }

    private void showRecipeDetails() {
        recipesContainer.getChildren().clear();
        recipesContainer.getChildren().add(recipeDetails);
    }

    private ObservableList<Recipe> getDummyRecipeList() {
        List<GristPart> fermentables = new LinkedList<>();
        List<HopAddition> hops = new LinkedList<>();
        List<YeastAddition<?>> yeasts = new LinkedList<>();

        fermentables.add(new GristPart(new Malt("Münich", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN), new Kilograms(0.3)));
        hops.add(new HopAddition(new Hop("Centennial", new Percentage(10)), new Minutes(60), new Grams(2.2)));
        yeasts.add(new YeastAddition<>(new Yeast("US-05", "Safale", new Percentage(88)), new Grams(11)));

        IngredientsList ingredientsList = new IngredientsList(fermentables, hops, yeasts);

        return FXCollections.observableArrayList(new Recipe(ingredientsList, null, BeerType.ALE, "Dodo IPA"));
    }
}

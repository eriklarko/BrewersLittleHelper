package se.angstroms.blh.anders.view.recipe;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
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
import org.blh.formuladecorator.FullContext;
import org.blh.recipe.attempts.composite.BasicRecipe;
import org.blh.recipe.attempts.composite.Recipe;
import org.blh.recipe.uncategorized.IngredientsList;
import se.angstroms.blh.anders.view.recipe.details.RecipeDetailsPresenter;
import se.angstroms.blh.anders.view.recipe.list.RecipeListPresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipesTab extends VBox {

	@Inject
	private FullContext fullContext;

	@FXML
	private RecipeListPresenter recipeList;

	@FXML
    private RecipeDetailsPresenter recipeDetails;

	public RecipesTab() {
		CustomControl.setup(this);

		recipeList.selectedRecipeProperty().addListener((ObservableValue<? extends Recipe> ov, Recipe oldValue, Recipe newValue) -> {
			fullContext.setRecipe(newValue);
			recipeDetails.recipeProperty().set(newValue);
            if (oldValue != newValue) {
				showRecipeDetails();
			}
        });

        recipeList.setRecipes(getDummyRecipeList());

		showRecipeList();
	}

	private void showRecipeList() {
		this.getChildren().clear();
		this.getChildren().add(recipeList);
    }

    private void showRecipeDetails() {
		this.getChildren().clear();
		this.getChildren().add(recipeDetails);
    }

    private ObservableList<Recipe> getDummyRecipeList() {
        List<GristPart> fermentables = new LinkedList<>();
        List<HopAddition> hops = new LinkedList<>();
        List<YeastAddition<?>> yeasts = new LinkedList<>();

        fermentables.add(new GristPart(new Malt("Münich", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN), new Kilograms(0.3)));
        hops.add(new HopAddition(new Hop("Centennial", new Percentage(10)), new Minutes(60), new Grams(2.2)));
        yeasts.add(new YeastAddition<>(new Yeast("US-05", "Safale", new Percentage(88)), new Grams(11)));

        IngredientsList ingredientsList = new IngredientsList(fermentables, hops, yeasts);
        Recipe recipe = new BasicRecipe(ingredientsList, null, BeerType.ALE, "Dodo IPA");

        return FXCollections.observableArrayList(recipe);
    }
}

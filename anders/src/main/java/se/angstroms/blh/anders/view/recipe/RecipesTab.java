package se.angstroms.blh.anders.view.recipe;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.context.FullContextInitializer;
import se.angstroms.blh.anders.uncategorized.context.InitializerException;
import se.angstroms.blh.anders.view.recipe.details.RecipeDetailsPresenter;
import se.angstroms.blh.anders.view.recipe.selector.RecipeSelectorPresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author eriklark
 */
public class RecipesTab extends VBox {

	@FXML
	private RecipeSelectorPresenter recipeSelector;

	@FXML
    private RecipeDetailsPresenter recipeDetails;

	public RecipesTab() {
		CustomControl.setup(this);

		recipeSelector.selectedRecipeProperty().addListener((ObservableValue<? extends FullContext> ov, FullContext oldValue, FullContext newValue) -> {
			recipeDetails.recipeProperty().set(newValue);
            if (oldValue != newValue) {
				showRecipeDetails();
			}
        });

		recipeSelector.availableRecipesProperty().set(getDummyRecipeList());

		showRecipeList();
	}

	private void showRecipeList() {
		this.getChildren().clear();
		this.getChildren().add(recipeSelector);
    }

    private void showRecipeDetails() {
		this.getChildren().clear();
		this.getChildren().add(recipeDetails);
    }

    private ObservableList<FullContext> getDummyRecipeList() {
        List<GristPart> fermentables = new LinkedList<>();
        List<HopAddition> hops = new LinkedList<>();
        List<YeastAddition<?>> yeasts = new LinkedList<>();

        fermentables.add(new GristPart(new Malt("Münich", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN), new Kilograms(0.3)));
        hops.add(new HopAddition(new Hop("Centennial", new Percentage(10)), new Minutes(60), new Grams(2.2)));
        yeasts.add(new YeastAddition<>(new Yeast("US-05", "Safale", new Percentage(88)), new Grams(11)));

        try {
            FullContext recipe = new FullContext();
            recipe.nameProperty().set("Dodo IPA");
            recipe.beerTypeProperty().set(BeerType.ALE);
            recipe.getIngredientsList().setFermentables(fermentables);
            recipe.getIngredientsList().setHopAdditions(hops);
            recipe.getIngredientsList().setYeastAdditions(yeasts);
            return FXCollections.observableArrayList(recipe);
        } catch (InitializerException ex) {
            throw new RuntimeException("Unable to build dummy recipe", ex);
        }
    }
}

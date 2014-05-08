package se.angstroms.blh.anders.view.recipe.details;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import se.angstroms.blh.anders.view.recipe.details.data.RecipeValuesPresenter;
import se.angstroms.blh.anders.view.recipe.details.ingredientslist.IngredientsListPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.blh.recipe.attempts.composite.Recipe;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Shows the details of a recipe
 *
 * @author Thinner
 */
public class RecipeDetailsPresenter extends VBox {

    @FXML private Label recipeName;
    @FXML private IngredientsListPresenter ingredientsList;
    @FXML private RecipeValuesPresenter recipeValues;

	private final ObjectProperty<Recipe> recipeProperty;

    public RecipeDetailsPresenter() {
        CustomControl.setup(this);

		recipeProperty = new SimpleObjectProperty<>();
		recipeProperty.addListener(new ChangeListener<Recipe>() {

			@Override
			public void changed(ObservableValue<? extends Recipe> ov, Recipe t, Recipe newValue) {
				recipeName.setText(newValue.getName());
				ingredientsList.ingredientsListProperty().set(newValue.getIngredientsList());
			}
		});
		recipeValues.recipeProperty().bind(recipeProperty);
    }

	public ObjectProperty<Recipe> recipeProperty() {
		return recipeProperty;
	}
}

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
import se.angstroms.blh.anders.context.FullContext;
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

	private final ObjectProperty<FullContext> recipeProperty;

    public RecipeDetailsPresenter() {
        CustomControl.setup(this);

		recipeProperty = new SimpleObjectProperty<>();
        recipeValues.recipeProperty().bind(recipeProperty);
		recipeProperty.addListener(new ChangeListener<FullContext>() {

			@Override
			public void changed(ObservableValue<? extends FullContext> ov, FullContext t, FullContext newValue) {
				recipeName.setText(newValue.nameProperty().get());
				ingredientsList.ingredientsListProperty().set(newValue.getIngredientsList());
			}
		});
    }

	public ObjectProperty<FullContext> recipeProperty() {
		return recipeProperty;
	}
}

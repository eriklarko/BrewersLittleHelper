package se.angstroms.blh.anders.view.recipe.selector;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import org.blh.recipe.attempts.composite.Recipe;

/**
 *
 * @author eriklark
 */
public interface RecipeSelector {

	String getName();

	ListProperty<Recipe> availableRecipesProperty();

	ObjectProperty<Recipe> selectedRecipeProperty();

	Node getGUI();
}

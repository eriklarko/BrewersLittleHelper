package se.angstroms.blh.anders.view.recipe.selector;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import se.angstroms.blh.anders.context.FullContext;

/**
 *
 * @author eriklark
 */
public interface RecipeSelector {

	String getName();

	ListProperty<FullContext> availableRecipesProperty();

	ObjectProperty<FullContext> selectedRecipeProperty();

	Node getGUI();
}

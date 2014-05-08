package se.angstroms.blh.anders.view.recipe.details.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.blh.core.unit.bitterness.IBU;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.NewIOCV;
import org.blh.formuladecorator.formulas.decorated.bitterness.DecoratedTinseth;
import org.blh.recipe.attempts.composite.Recipe;
import se.angstroms.blh.anders.view.recipe.details.data.value.ValuePresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class RecipeValuesPresenter extends VBox {

	@Inject
	private FullContext fullContext;

    @FXML
    private ValuePresenter ibuComponent;

	private final ObjectProperty<Recipe> recipeProperty;

    public RecipeValuesPresenter() {
        CustomControl.setup(this);

		recipeProperty = new SimpleObjectProperty<>();
		recipeProperty.addListener(new ChangeListener<Recipe>() {

			@Override
			public void changed(ObservableValue<? extends Recipe> ov, Recipe t, Recipe t1) {
				recalculateValues();
			}
		});
    }

	private void registerValuesToContextVariables() {

	}

	public ObjectProperty<Recipe> recipeProperty() {
		return recipeProperty;
	}

    private void recalculateValues() {
        this.getChildren().stream().filter((child) -> (child instanceof ValuePresenter)).map((child) -> (ValuePresenter) child).forEach((val) -> {
			NewIOCV<IBU> newIOCV = new NewIOCV<>(fullContext, IBU.class);
			val.setInputtedOrCalculatedValue(newIOCV);
			newIOCV.calculateUsing(new DecoratedTinseth());
        });
    }
}

package se.angstroms.blh.anders.view.recipe.details.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import org.blh.core.unit.bitterness.IBU;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.NewIOCV;
import org.blh.formuladecorator.formulas.ObservableFormula;
import org.blh.formuladecorator.formulas.observable.bitterness.ObservableTinseth;
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

    public RecipeValuesPresenter() {
        CustomControl.setup(this);

		registerValuesToContextVariables();
    }

	private void registerValuesToContextVariables() {
		// TODO: Use some kind of attribute in the ValuePresenter instead
		ibuComponent.setInputtedOrCalculatedValue(asd(IBU.class, new ObservableTinseth(fullContext)));
	}

	private <T extends Unit<?>> NewIOCV<T> asd(Class<T> clazz, ObservableFormula<T> f) {
		return new NewIOCV<>(clazz, f);
	}
}

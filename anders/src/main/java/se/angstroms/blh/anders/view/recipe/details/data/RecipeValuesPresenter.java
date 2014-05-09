package se.angstroms.blh.anders.view.recipe.details.data;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import org.blh.core.unit.bitterness.IBU;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import org.blh.formuladecorator.formulas.ObservableFormula;
import org.blh.formuladecorator.formulas.observable.bitterness.ObservableTinseth;
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

	private <T extends Unit<?>> InputtedOrCalculatedValue<T> asd(Class<T> clazz, ObservableFormula<T> f) {
		return new InputtedOrCalculatedValue<>(f);
	}
}

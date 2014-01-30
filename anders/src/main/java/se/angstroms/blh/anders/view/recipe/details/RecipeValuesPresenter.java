package se.angstroms.blh.anders.view.recipe.details;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import se.angstroms.blh.anders.view.controls.ValueComponentPresenter;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class RecipeValuesPresenter extends VBox {

    @FXML
    private ValueComponentPresenter ibuComponent;

    public RecipeValuesPresenter() {
        CustomControl.setup(this);
    }

    public void recalculateValues() {
        this.getChildren().stream().filter((child) -> (child instanceof ValueComponentPresenter)).map((child) -> (ValueComponentPresenter) child).forEach((val) -> {
            val.recalculateValue();
        });
    }
}

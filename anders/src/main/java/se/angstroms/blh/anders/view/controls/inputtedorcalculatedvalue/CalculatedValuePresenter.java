package se.angstroms.blh.anders.view.controls.inputtedorcalculatedvalue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class CalculatedValuePresenter extends HBox {

    @FXML
    private Label value;

    public CalculatedValuePresenter() {
        CustomControl.setup(this);
    }

    @FXML
    private void onValueClicked() {
        System.out.println("Blurred the liiiiiines!");
    }
}

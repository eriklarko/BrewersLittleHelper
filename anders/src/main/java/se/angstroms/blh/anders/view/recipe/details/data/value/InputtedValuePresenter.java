package se.angstroms.blh.anders.view.recipe.details.data.value;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class InputtedValuePresenter extends HBox implements ChangeListener<Boolean> {

    @FXML
    private TextField input;

    public InputtedValuePresenter() {
        CustomControl.setup(this);

        input.focusedProperty().addListener(this);
    }

	public String getValue() {
		return input.getText();
	}

	public void setValue(String text) {
		input.setText(text);
	}

	public StringProperty valueProperty() {
		return input.textProperty();
	}

    @Override
    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean blurred) {
        if (blurred) {
            System.out.println("Blurred the liiiiiines!");
        }
    }
}

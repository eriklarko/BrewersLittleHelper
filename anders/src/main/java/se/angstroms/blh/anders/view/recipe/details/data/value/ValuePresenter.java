package se.angstroms.blh.anders.view.recipe.details.data.value;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class ValuePresenter extends HBox {

	@FXML
    private Label title;

    @FXML
    private Pane valueContainer;

    private final InputtedValuePresenter inputtedValue;
    private final CalculatedValuePresenter calculatedValue;

    public ValuePresenter() {
        CustomControl.setup(this);

        inputtedValue = new InputtedValuePresenter();
        calculatedValue = new CalculatedValuePresenter();
    }

	public String getTitle() {
        return this.title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public StringProperty titleProperty() {
        return this.title.textProperty();
    }

	public void setInputtedOrCalculatedValue(InputtedOrCalculatedValue<?> inputtedOrCalculatedValue) {

        inputtedOrCalculatedValue.isInputtedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean isInputted) {
				handleInputtedState(isInputted);
			}
		});
		inputtedOrCalculatedValue.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> ov, Object oldValue, Object newValue) {
				System.out.println("Updating " + title.getText() + " to " + String.valueOf(newValue));
				inputtedValue.setValue(String.valueOf(newValue));
				calculatedValue.setValue(String.valueOf(newValue));
			}
		});
        handleInputtedState(inputtedOrCalculatedValue.isInputted());
    }

    private void showInputtedValue() {
        valueContainer.getChildren().clear();
        valueContainer.getChildren().add(inputtedValue);
    }

    private void showCalculatedValue() {
        valueContainer.getChildren().clear();
        valueContainer.getChildren().add(calculatedValue);
    }

    private void handleInputtedState(Boolean isInputted) {
        if(isInputted) {
            showInputtedValue();
        } else {
            showCalculatedValue();
        }
    }

    @FXML
    private void showAvailableFormulas() {

    }
}

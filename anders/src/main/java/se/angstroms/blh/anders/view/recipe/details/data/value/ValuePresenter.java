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
import org.blh.formuladecorator.NewIOCV;
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
    private final ObjectProperty<NewIOCV<?>> inputtedOrCalculatedValueProperty;

    public ValuePresenter() {
        CustomControl.setup(this);

        inputtedOrCalculatedValueProperty = new SimpleObjectProperty<>();
		inputtedOrCalculatedValueProperty.addListener(new ChangeListener<NewIOCV<?>>() {

			@Override
			public void changed(ObservableValue<? extends NewIOCV<?>> ov, NewIOCV<?> t, NewIOCV<?> t1) {
				setInputtedOrCalculatedValue(t1);
			}
		});

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

	public NewIOCV<?> getInputtedOrCalculatedValue() {
		return this.inputtedOrCalculatedValueProperty.get();
	}

	public void setInputtedOrCalculatedValue(NewIOCV<?> inputtedOrCalculatedValue) {
        this.inputtedOrCalculatedValueProperty.set(inputtedOrCalculatedValue);

        inputtedOrCalculatedValue.getIsInputtedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean isInputted) {
				handleInputtedState(isInputted);
			}
		});
		inputtedOrCalculatedValue.getValueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> ov, Object t, Object t1) {
				inputtedValue.setValue(String.valueOf(t1));
				calculatedValue.setValue(String.valueOf(t1));
			}
		});
        handleInputtedState(inputtedOrCalculatedValue.isInputted());
    }

    public ObjectProperty<NewIOCV<?>> inputtedOrCalculatedValueProperty() {
        return inputtedOrCalculatedValueProperty;
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

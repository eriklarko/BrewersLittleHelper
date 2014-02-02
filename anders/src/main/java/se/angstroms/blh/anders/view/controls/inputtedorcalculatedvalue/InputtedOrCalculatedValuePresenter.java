package se.angstroms.blh.anders.view.controls.inputtedorcalculatedvalue;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.blh.formuladecorator.NewIOCV;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class InputtedOrCalculatedValuePresenter extends HBox implements ChangeListener<Boolean>{

    @FXML
    private Pane valueContainer;

    private final InputtedValuePresenter inputtedValue;
    private final CalculatedValuePresenter calculatedValue;
    private final ReadOnlyObjectWrapper<NewIOCV<?>> inputtedOrCalculatedValueProperty;

    public InputtedOrCalculatedValuePresenter() {
        CustomControl.setup(this);
        inputtedOrCalculatedValueProperty = new ReadOnlyObjectWrapper<>();

        inputtedValue = new InputtedValuePresenter();
        calculatedValue = new CalculatedValuePresenter();
    }

    public NewIOCV<?> getInputtedOrCalculatedValue() {
        return inputtedOrCalculatedValueProperty.get();
    }

    public void setInputtedOrCalculatedValue(NewIOCV<?> inputtedOrCalculatedValue) {
        this.inputtedOrCalculatedValueProperty.set(inputtedOrCalculatedValue);
        inputtedOrCalculatedValue.getIsInputtedProperty().addListener(this);
        handleInputtedState(inputtedOrCalculatedValue.isInputted());
    }

    public ReadOnlyObjectProperty<NewIOCV<?>> inputtedOrCalculatedValueProperty() {
        return inputtedOrCalculatedValueProperty.getReadOnlyProperty();
    }

    private void showInputtedValue() {
        valueContainer.getChildren().clear();
        valueContainer.getChildren().add(inputtedValue);
    }

    private void showCalculatedValue() {
        valueContainer.getChildren().clear();
        valueContainer.getChildren().add(calculatedValue);
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean isInputted) {
        handleInputtedState(isInputted);
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

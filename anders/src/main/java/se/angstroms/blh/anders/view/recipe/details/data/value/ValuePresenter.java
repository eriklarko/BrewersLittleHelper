package se.angstroms.blh.anders.view.recipe.details.data.value;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue.STATE;
import se.angstroms.blh.anders.context.value.parsing.ParseException;
import se.angstroms.blh.anders.context.value.UnitStringFormatter;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParser;
import se.angstroms.blh.anders.view.recipe.details.data.value.InputtedValuePresenter.CommitEvent;
import se.angstroms.blh.anders.view.util.CustomControl;


/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class ValuePresenter<T extends Unit<?>> extends HBox {

    @FXML
    private InputtedValuePresenter inputtedValue;

    @FXML
    private CalculatedValuePresenter calculatedValue;

	@Inject
	private UnitStringFormatter unitStringFormatter;

	private InputtedOrCalculatedValue<T> inputtedOrCalculatedValue;
	private boolean scaryFuckingIgnoreChangeEvent = false;
    private Button goBackToCalculatedButton;

	public ValuePresenter(InputtedOrCalculatedValue<T> inputtedOrCalculatedValue, UnitStringParser<T> parser) {
        CustomControl.setup(this);

		inputtedValue.addOnTextChangedListener(new EventHandler<CommitEvent>() {

            private String lastLegal = "";

			@Override
			public void handle(CommitEvent t) {
                try {
                    T newValue = parser.parse(t.getText());
                    scaryFuckingIgnoreChangeEvent = true;
                    lastLegal = t.getText();
                    ValuePresenter.this.inputtedOrCalculatedValue.set(newValue);
                } catch (ParseException ex) {
                    inputtedValue.setValue(lastLegal);

                    // TODO: Handle exceptions
                    System.err.println(ex.getMessage());
                }
			}
		});
		calculatedValue.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				ValuePresenter.this.inputtedOrCalculatedValue.set(ValuePresenter.this.inputtedOrCalculatedValue.get());
			}
		});
		setInputtedOrCalculatedValue(inputtedOrCalculatedValue);
	}

	public void setInputtedOrCalculatedValue(InputtedOrCalculatedValue<T> inputtedOrCalculatedValue) {
		this.inputtedOrCalculatedValue = inputtedOrCalculatedValue;
        inputtedOrCalculatedValue.stateProperty().addListener(new ChangeListener<STATE>() {

            @Override
            public void changed(ObservableValue<? extends STATE> ov, STATE oldState, STATE newState) {
                handleInputtedState(newState == STATE.INPUTTED);
            }
		});

		inputtedOrCalculatedValue.valueProperty().addListener(new ChangeListener<T>() {

			@Override
			public void changed(ObservableValue<? extends T> ov, T oldValue, T newValue) {
				if (scaryFuckingIgnoreChangeEvent) {
					scaryFuckingIgnoreChangeEvent = false;
					return;
				}

                setValue(newValue);
				triggerValueChangedVisualization();
			}
		});

        //if (inputtedOrCalculatedValue.stateProperty().get() == STATE.INPUTTED) {
            setValue(inputtedOrCalculatedValue.get());
        //}

        handleInputtedState(inputtedOrCalculatedValue.stateProperty().get() == STATE.INPUTTED);
    }

    private void setValue(T newValue) {
        String valueAsString = unitStringFormatter.format(newValue);

        inputtedValue.setValue(valueAsString);
        calculatedValue.setValue(valueAsString);
    }

    private void showInputtedValue() {
        inputtedValue.setVisible(true);
        calculatedValue.setVisible(false);
    }

    private void showCalculatedValue() {
        inputtedValue.setVisible(false);
        calculatedValue.setVisible(true);
    }

    private void handleInputtedState(Boolean isInputted) {
        if(isInputted) {
            showInputtedValue();
        } else {
            showCalculatedValue();
        }
    }

	private void triggerValueChangedVisualization() {
		FadeTransition animation = new FadeTransition(Duration.millis(250), this);
		animation.setFromValue(0.4);
		animation.setToValue(1);
		animation.setCycleCount(1);
		animation.setAutoReverse(false);
		animation.play();
	}

    public Button getGoBackToCalculatedButton() {
        if (goBackToCalculatedButton == null) {
            goBackToCalculatedButton = new Button("M");

            goBackToCalculatedButton.visibleProperty().bind(this.inputtedValue.visibleProperty());
            goBackToCalculatedButton.setOnAction((ActionEvent t) ->
                ValuePresenter.this.inputtedOrCalculatedValue.enterCalculatedState()
            );
        }
        return goBackToCalculatedButton;
    }
}

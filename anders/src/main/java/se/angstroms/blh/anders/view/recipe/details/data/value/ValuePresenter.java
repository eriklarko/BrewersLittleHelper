package se.angstroms.blh.anders.view.recipe.details.data.value;

import com.airhacks.afterburner.injection.InjectionProvider;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Builder;
import javafx.util.Duration;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import org.blh.formuladecorator.InputtedOrCalculatedValue.STATE;
import se.angstroms.blh.anders.uncategorized.ValueId;
import se.angstroms.blh.anders.uncategorized.InputtedOrCalculatedValueFactory;
import se.angstroms.blh.anders.uncategorized.NoDefaultFormulaException;
import se.angstroms.blh.anders.uncategorized.ParseException;
import se.angstroms.blh.anders.uncategorized.UnitStringFormatter;
import se.angstroms.blh.anders.uncategorized.UnitStringParser;
import se.angstroms.blh.anders.uncategorized.UnitStringParserFactory;
import se.angstroms.blh.anders.view.recipe.details.data.value.InputtedValuePresenter.CommitEvent;
import se.angstroms.blh.anders.view.util.CustomControl;


/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class ValuePresenter<T extends Unit<?>> extends HBox {

	public static class ValuePresenterBuilder implements Builder<ValuePresenter> {

        @Inject
        private InputtedOrCalculatedValueFactory inputtedOrCalculatedValueFactory;

        @Inject
        private UnitStringParserFactory parserFactory;

		private ValueId type;

        public ValuePresenterBuilder() {
            InjectionProvider.injectMembers(ValuePresenterBuilder.class, this);
        }

		public ValueId getType() {
			return type;
		}

		public void setType(ValueId type) {
			this.type = type;
		}

		@Override
		public ValuePresenter build() {
			ValuePresenter valuePresenter;
			if (type == null) {
				throw new RuntimeException("Mwääää, typen måste vara satt!");
			} else {
				try {
					valuePresenter = new ValuePresenter(
                            inputtedOrCalculatedValueFactory.fromDefaultFormula(type),
                            parserFactory.getParserFor(type)
                    );
				} catch (NoDefaultFormulaException ex) {
					throw new RuntimeException(ex);
				}
			}

			return valuePresenter;
		}
	}

    @FXML
    private InputtedValuePresenter inputtedValue;

    @FXML
    private CalculatedValuePresenter calculatedValue;

	@Inject
	private UnitStringFormatter unitStringFormatter;

	private InputtedOrCalculatedValue<T> inputtedOrCalculatedValue;
	private boolean scaryFuckingIgnoreChangeEvent = false;
    private Button goBackToCalculatedButton;

	private ValuePresenter(InputtedOrCalculatedValue<T> inputtedOrCalculatedValue, UnitStringParser<T> parser) {
        CustomControl.setup(this);

		inputtedValue.addOnTextChangedListener(new EventHandler<CommitEvent>() {

            private String lastLegal = "";

			@Override
			public void handle(CommitEvent t) {
                try {
                    T newValue = parser.parse(t.getText());
                    scaryFuckingIgnoreChangeEvent = true;
                    lastLegal = t.getText();
                    ValuePresenter.this.inputtedOrCalculatedValue.setValue(newValue);
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
				ValuePresenter.this.inputtedOrCalculatedValue.setValue(ValuePresenter.this.inputtedOrCalculatedValue.value());
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
        if (inputtedOrCalculatedValue.stateProperty().get() == STATE.INPUTTED) {
            setValue(inputtedOrCalculatedValue.value());
        }

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
		FadeTransition animation = new FadeTransition(Duration.millis(500), this);
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

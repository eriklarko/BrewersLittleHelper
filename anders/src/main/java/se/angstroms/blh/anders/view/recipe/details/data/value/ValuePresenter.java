package se.angstroms.blh.anders.view.recipe.details.data.value;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Builder;
import javafx.util.Duration;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.ValueId;
import se.angstroms.blh.anders.view.util.CustomControl;
import se.angstroms.blh.anders.util.InputtedOrCalculatedValueFactory;
import se.angstroms.blh.anders.util.NoDefaultFormulaException;
import se.angstroms.blh.anders.util.UnitStringFormatter;
import se.angstroms.blh.anders.view.recipe.details.data.value.InputtedValuePresenter.CommitEvent;


/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class ValuePresenter<T extends Unit<?>> extends HBox {

	public static class ValuePresenterBuilder implements Builder<ValuePresenter> {

		private ValueId type;

		public ValueId getType() {
			return type;
		}

		public void setType(ValueId type) {
			this.type = type;
		}

		@Override
		public ValuePresenter build() {
			ValuePresenter object;
			if (type == null) {
				throw new RuntimeException("Mwääää måste vara satt!");
			} else {
				try {
					object = new ValuePresenter(InputtedOrCalculatedValueFactory.getInstance().fromDefaultFormula(type));
				} catch (NoDefaultFormulaException ex) {
					throw new RuntimeException(ex);
				}
			}

			return object;
		}
	}

    @FXML
    private Pane valueContainer;

	@Inject
	private UnitStringFormatter unitStringFormatter;

    private final InputtedValuePresenter inputtedValue;
    private final CalculatedValuePresenter calculatedValue;
	private InputtedOrCalculatedValue<T> inputtedOrCalculatedValue;
	private boolean scaryFuckingIgnoreChangeEvent = false;


	private ValuePresenter(InputtedOrCalculatedValue<T> inputtedOrCalculatedValue) {
        CustomControl.setup(this);

        inputtedValue = new InputtedValuePresenter();
		inputtedValue.addOnTextChangedListener(new EventHandler<CommitEvent>() {

			@Override
			public void handle(CommitEvent t) {
				Double d = Double.parseDouble(t.getText());
				scaryFuckingIgnoreChangeEvent = true;
				ValuePresenter.this.inputtedOrCalculatedValue.setValue((T) new SpecificGravity(d));
			}
		});
        calculatedValue = new CalculatedValuePresenter();
		calculatedValue.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				showInputtedValue();
			}
		});
		setInputtedOrCalculatedValue(inputtedOrCalculatedValue);
	}

	public void setInputtedOrCalculatedValue(InputtedOrCalculatedValue<T> inputtedOrCalculatedValue) {
		this.inputtedOrCalculatedValue = inputtedOrCalculatedValue;
        inputtedOrCalculatedValue.isInputtedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean isInputted) {
				handleInputtedState(isInputted);
			}
		});
		inputtedOrCalculatedValue.valueProperty().addListener(new ChangeListener<T>() {

			@Override
			public void changed(ObservableValue<? extends T> ov, T oldValue, T newValue) {
				if (scaryFuckingIgnoreChangeEvent) {
					scaryFuckingIgnoreChangeEvent = false;
					return;
				}

				String valueAsString = unitStringFormatter.format(newValue);

				inputtedValue.setValue(valueAsString);
				calculatedValue.setValue(valueAsString);

				triggerValueChangedVisualization();
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

	private void triggerValueChangedVisualization() {
		FadeTransition animation = new FadeTransition(Duration.millis(500), this);
		animation.setFromValue(0.4);
		animation.setToValue(1);
		animation.setCycleCount(1);
		animation.setAutoReverse(false);
		animation.play();
	}

    @FXML
    private void showAvailableFormulas() {

    }
}

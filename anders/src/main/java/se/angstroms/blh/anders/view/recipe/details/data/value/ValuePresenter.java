package se.angstroms.blh.anders.view.recipe.details.data.value;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Builder;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.FormulaFactory;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.view.util.CustomControl;


/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class ValuePresenter extends HBox {

	public static class ValuePresenterBuilder implements Builder<ValuePresenter> {

		private String title;
		private Class<? extends Unit<?>> clazz;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setClazz(Class<? extends Unit<?>> clazz) {
			this.clazz = clazz;
		}

		public Class<? extends Unit<?>> getClazz() {
			return clazz;
		}

		@Override
		public ValuePresenter build() {
			ValuePresenter a;
			if (clazz == null) {
				a = new ValuePresenter();
			} else {
				a = new ValuePresenter(clazz);
			}
			a.setTitle(title);

			return a;
		}
	}

	@FXML
    private Label title;

    @FXML
    private Pane valueContainer;

    private final InputtedValuePresenter inputtedValue;
    private final CalculatedValuePresenter calculatedValue;

    private ValuePresenter() {
        CustomControl.setup(this);

        inputtedValue = new InputtedValuePresenter();
        calculatedValue = new CalculatedValuePresenter();
    }

	private <T extends Unit<?>> ValuePresenter(Class<T> clazz) {
		this();
		InputtedOrCalculatedValue<T> a = new InputtedOrCalculatedValue<>(FormulaFactory.getInstance().lol(clazz));
		setInputtedOrCalculatedValue(a);
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

	public void setInputtedOrCalculatedValue(InputtedOrCalculatedValue<? extends Unit<?>> inputtedOrCalculatedValue) {
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

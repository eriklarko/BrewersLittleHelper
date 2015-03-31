package se.angstroms.blh.anders.view.formulaexplorer;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.value.CalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedValue;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.recipe.details.data.value.InputtedValuePresenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

public class VariableGridRow implements GridListView.GridRow<Value<?>>{

	private final SimpleObjectProperty<Value<?>> model;
	private final ToggleGroup toggleGroup;

	public VariableGridRow(Value<?> value, ToggleGroup toggleGroup) {
		 model = new SimpleObjectProperty<>(value);
		 this.toggleGroup = toggleGroup;
	}


	@Override
	public ObservableValue<Value<?>> getModel() {
		return model;
	}

	@Override
	public GridListView.GridRowColumnBuilder getColumns() {
		if (model.get() == null) {
			return new GridListView.GridRowColumnBuilder()
				.addColumn(new Label("Variable"))
				.addColumn(new Label("Value"))
				.addColumn(new Label("On X-axis"));
		}

		return new GridListView.GridRowColumnBuilder()
				.addColumn(label())
				.addColumn(input())
				.addColumn(freeRadio());
	}

	private Node label() {
		Label label = new Label();
		label.setText(model.get().getValueType().getHumanReadable());
		return label;
	}

	private Node input() {
		if (model.get() instanceof CalculatedValue) {
			return new Label(model.get().asString());
		}

		if (model.get() instanceof InputtedOrCalculatedValue) {
			InputtedOrCalculatedValue iocv = (InputtedOrCalculatedValue) model.get();
			InputtedValue iv = inputtedValueFromIOCV(iocv);

			InputtedValuePresenter input = new InputtedValuePresenter();
			input.setValue(iv);
			return input;
		}

		if (model.get() instanceof InputtedValue) {
			InputtedValuePresenter input = new InputtedValuePresenter();
			input.setValue((InputtedValue) model.get());
			return input;
		}

		return new Label("N/A");
	}

	private <T extends Unit<?>> InputtedValue inputtedValueFromIOCV(InputtedOrCalculatedValue<T> iocv) {
		InputtedValue inputtedValue = new InputtedValue(iocv.getValueType(), iocv.get());
		inputtedValue.addListener(new ChangeListener<T>() {

			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				iocv.set(newValue);
			}
		});
		return inputtedValue;
	}


	private Node freeRadio() {
		if (!(model.get() instanceof InputtedValue || model.get() instanceof InputtedOrCalculatedValue)) {
			return new Label("");
		}

		RadioButton rb = new RadioButton();
		rb.setTooltip(new Tooltip("Put variable on the X-axis"));
		rb.setToggleGroup(toggleGroup);

		if (model.get() instanceof InputtedOrCalculatedValue) {
			rb.setUserData(inputtedValueFromIOCV((InputtedOrCalculatedValue) model.get()));
		} else {
			rb.setUserData(model.get());
		}

		return rb;
	}
}

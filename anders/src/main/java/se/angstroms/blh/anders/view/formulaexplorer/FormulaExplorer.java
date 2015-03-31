package se.angstroms.blh.anders.view.formulaexplorer;

import java.util.function.Consumer;

import org.blh.core.unit.DoubleUnit;
import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedValue;
import se.angstroms.blh.anders.context.value.UnitStringFormatter;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.formulas.PartialObservableFormula;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.common.GridListView.GridRow;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * TODO: Allow all dependencies to be set to some values
 *
 * TODO: Make sure the changing of the free variable does not change the real FullContext
 *
 * @author eriklark
 */
public class FormulaExplorer<T extends Unit<?>> extends BorderPane {

	private final ObservableFormula<T> formulaToExplore;
	private final UnitStringFormatter unitStringFormatter = new UnitStringFormatter();

	public FormulaExplorer(ObservableFormula<T> formulaToExplore) {
		this.formulaToExplore = new PartialObservableFormula(formulaToExplore.getClass()).bindTo(new FullContext());
		//this.formulaToExplore = formulaToExplore;

		Label valueDetails = new Label();
		BorderPane.setAlignment(valueDetails, Pos.CENTER_RIGHT);

		LineChart graph = buildGraph();
		Node dependenciesInputs = buildDependenciesInputs((value) -> {
			LineChart.Series series = new LineChart.Series();
			series.setName(unitStringFormatter.getUnitName(value.getValueType().getUnit()));

			T currentValue = value.get();
			if (currentValue instanceof DoubleUnit) {
				DoubleUnit du = (DoubleUnit) currentValue;
				// TODO: The following values should come from some place :) Maybe from the beer style?
				double min = 0.9;
				double max = 2;
				double step = (max - min)/100;
				currentValue = du.deriveNew(min);

				for (int x = 0; x < Math.ceil(max / step); x++) {
					final DoubleUnit daauuu = (DoubleUnit) currentValue;
					Double currentValueAsDouble = daauuu.value();
					T nextValue = du.deriveNew(currentValueAsDouble + step);
					value.set(nextValue);
					DoubleUnit result = (DoubleUnit) this.formulaToExplore.calc();

					XYChart.Data data = new LineChart.Data(currentValueAsDouble, result.value());
					data.nodeProperty().addListener(new ChangeListener() {

						@Override
						public void changed(ObservableValue observable, Object oldValue, Object newValue) {
							data.getNode().setOnMouseEntered((MouseEvent event) -> valueDetails.setText(unitStringFormatter.format(daauuu) + ", " + unitStringFormatter.format(result)));
							data.getNode().setOnMouseExited((MouseEvent event) -> valueDetails.setText(""));
						}
					});

					series.getData().add(data);
					currentValue = nextValue;
				}
			}

			graph.getData().clear();
			graph.getData().add(series);

		});

		this.setLeft(dependenciesInputs);
		this.setTop(valueDetails);
		this.setCenter(graph);
	}

	private Node buildDependenciesInputs(Consumer<InputtedValue<T>> onFreeVariableChanged) {
		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				onFreeVariableChanged.accept((InputtedValue<T>) toggleGroup.getSelectedToggle().getUserData());
			}
		});

		GridListView<Value<?>> gridList = new GridListView<Value<?>>().setOddRowClass(null).setEvenRowClass(null);
		ObservableList<Value<?>> dependencies = FXCollections.observableArrayList();
		dependencies.add(null);// Ugly hack to get headers on the grid
		dependencies.addAll(formulaToExplore.getDependencies());

		gridList.setData(dependencies, (dependency) -> buildDependencyInput(dependency, toggleGroup));

		return gridList;
	}

	private GridRow<Value<?>> buildDependencyInput(Value<?> dependency, ToggleGroup toggleGroup) {
		return new VariableGridRow(dependency, toggleGroup);
	}

	private LineChart buildGraph() {
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setForceZeroInRange(false);

		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel(unitStringFormatter.getUnitName(formulaToExplore.calculates().iterator().next().getUnit()));
		yAxis.setAutoRanging(true);
		yAxis.setTickLabelsVisible(true);
		yAxis.setMinorTickVisible(true);
		yAxis.setTickMarkVisible(true);

		//creating the chart
		final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		// TODO: Set Cursor.CROSSHAIR on css class "chart-plot-background"

		lineChart.setTitle(formulaToExplore.getName());

		return lineChart;
	}

	public void populateDependenciesUsing(FullContext context) {

	}
}

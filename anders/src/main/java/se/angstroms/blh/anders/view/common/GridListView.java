package se.angstroms.blh.anders.view.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author eriklark
 */
public class GridListView<T> extends GridPane {

	public static ColumnConstraints percentageWidth(double percentage) {
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setPercentWidth(percentage);
		return columnConstraints;
	}

	private static <T> ObservableList<GridRow<T>> toGridRows(final ObservableList<T> models, final Function<T, GridRow<T>> creator) {

		List<GridRow<T>> gridRows = models.stream().map((T model) -> newRow(models, model, creator)).collect(Collectors.toList());

		final ObservableList<GridRow<T>> observableGridRows = FXCollections.observableList(gridRows);

        // Attach listener to the models that triggers a rethingie whenever
		// a model is added or removed to the list
		models.addListener(new ListChangeListener<T>() {

			@Override
			public void onChanged(ListChangeListener.Change<? extends T> c) {
				while (c.next()) {
					for (T t : c.getAddedSubList()) {
						if (c.wasReplaced()) {
							continue;
						}

						System.out.println("Added " + t + "             " + c);
						observableGridRows.add(newRow(models, t, creator));
					}

					// For each removed model, remove the corresponding GridRow
					for (T t : c.getRemoved()) {
						if (c.wasReplaced()) {
							continue;
						}
						Iterator<GridRow<T>> iterator = observableGridRows.iterator();
						while (iterator.hasNext()) {
							GridRow<T> gridRow = iterator.next();
							if (gridRow.getModel().getValue().equals(t)) {
								System.out.println("It seems " + t + " was removed      " + c);
								iterator.remove();
							}
						}
					}
				}
			}
		});

		return observableGridRows;
	}

	private static <T> GridRow<T> newRow(List<T> models, T model, Function<T, GridRow<T>> creator) {
		GridListView.GridRow<T> gridRow = creator.apply(model);

        // Register listener that triggers an update on the models list whenever a model changes.
		// Without this, only the model gets an update - not the list itself..
		gridRow.getModel().addListener((_1, old, n) -> {
			int index = models.indexOf(old);
			models.set(index, n);
		});

		return gridRow;
	}

	private String evenRowClass = "grid-list-view-even", oddRowClass = "grid-list-view-odd";
	private ObservableList<GridRow<T>> rows;
	private final ListChangeListener<GridRow<?>> rowsListener = (c) -> {
		layoutDataRows();
	};

	public GridListView() {
		this.getStyleClass().add("grid-list-view");
		this.getStylesheets().add("/styles/anders.css");
	}

	public GridListView(ObservableList<T> rows, Function<T, GridRow<T>> creator) {
		this();
		setData(rows, creator);
	}

	public GridListView<T> setEvenRowClass(String evenRowClass) {
		this.evenRowClass = evenRowClass;
		return this;
	}

	public GridListView<T> setOddRowClass(String oddRowClass) {
		this.oddRowClass = oddRowClass;
		return this;
	}

	public void setData(ObservableList<T> rows, Function<T, GridRow<T>> creator) {
		if (this.rows != null) {
			this.rows.removeListener(rowsListener);
		}
		this.rows = toGridRows(rows, creator);
		this.rows.addListener(rowsListener);
		layoutDataRows();
	}

	private void layoutDataRows() {
		this.getColumnConstraints().clear();
		this.getChildren().clear();

		int currentRow = 0;
		for (GridRow<T> row : rows) {
			int currentColumn = 0;
			for (Pair<ColumnConstraints, Node> pair : row.getColumns().getColumns()) {
				if (pair.getKey() != null && currentRow == 0) {
					this.getColumnConstraints().add(currentColumn, pair.getKey());
				}

				this.add(pair.getValue(), currentColumn, currentRow);
				if (currentRow % 2 == 0) {
					if (evenRowClass != null) {
						pair.getValue().getStyleClass().add(evenRowClass);
					}
				} else {
					if (oddRowClass != null) {
						pair.getValue().getStyleClass().add(oddRowClass);
					}
				}
				currentColumn++;
			}

			currentRow++;
		}
	}

	public static interface GridRow<T> {

		ObservableValue<T> getModel();

		GridRowColumnBuilder getColumns();
	}

	public static class GridRowColumnBuilder {

		private final Collection<Pair<ColumnConstraints, Node>> columns = new LinkedList<>();

		public GridRowColumnBuilder addColumn(Node node) {
			columns.add(new Pair<>(null, node));
			return this;
		}

		public GridRowColumnBuilder addColumn(Node node, ColumnConstraints columnConstraints) {
			columns.add(new Pair<>(columnConstraints, node));
			return this;
		}

		Collection<Pair<ColumnConstraints, Node>> getColumns() {
			return columns;
		}
	}
}

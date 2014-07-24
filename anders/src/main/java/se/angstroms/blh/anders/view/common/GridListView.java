package se.angstroms.blh.anders.view.common;

import javafx.beans.property.Property;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author eriklark
 */
public class GridListView extends GridPane {

    public static interface GridRow<T extends Property<?>> {

        T getModel();

        Iterable<Node> getNodes();
    }

    private ObservableList<GridRow<?>> rows;
    private final ListChangeListener<GridRow<?>> rowsListener = (c) -> {
        while(c.next());
        layoutDataRows();
    };

    public GridListView() {
    }

    public GridListView(ObservableList<GridRow<?>> rows) {
        setData(rows);
    }

    public void setData(ObservableList<GridRow<?>> rows) {
        if (this.rows != null) {
            this.rows.removeListener(rowsListener);
        }
        this.rows = rows;
        this.rows.addListener(rowsListener);
        layoutDataRows();
    }

    private void layoutDataRows() {
        this.getChildren().clear();

        int currentRow = 0;
        for (GridRow<?> row : rows) {
            int currentColumn = 0;
            for (Node node : row.getNodes()) {
                this.add(node, currentColumn, currentRow);
                currentColumn++;
            }

            currentRow++;
        }
    }
}

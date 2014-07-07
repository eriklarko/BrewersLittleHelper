package se.angstroms.blh.anders.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.core.ingredient.Hop;
import org.blh.core.unit.Percentage;

/**
 *
 * @author eriklark
 */
public class HopStore implements Store<Hop> {

    @Override
    public ObservableList<Hop> getAll() {
        return FXCollections.observableArrayList(
                new Hop("East Kent Goldings", new Percentage(5.7))
        );
    }

}

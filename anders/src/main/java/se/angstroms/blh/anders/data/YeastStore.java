package se.angstroms.blh.anders.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.core.ingredient.Yeast;
import org.blh.core.unit.Percentage;

/**
 *
 * @author eriklark
 */
public class YeastStore implements Store<Yeast> {

    @Override
    public ObservableList<Yeast> getAll() {
        return FXCollections.observableArrayList(
                new Yeast("WLP001", "White Labs", new Percentage(80)),
                new Yeast("WLP002", "White Labs", new Percentage(40))
        );
    }
}

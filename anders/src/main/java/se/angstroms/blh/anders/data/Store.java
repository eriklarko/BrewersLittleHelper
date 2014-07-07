package se.angstroms.blh.anders.data;

import javafx.collections.ObservableList;

/**
 *
 * @author eriklark
 */
public interface Store<T> {

    ObservableList<T> getAll();
}

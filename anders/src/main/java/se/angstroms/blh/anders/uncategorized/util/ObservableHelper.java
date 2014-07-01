package se.angstroms.blh.anders.uncategorized.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * A copy of com.sun.javafx.binding.ExpressionBinding for use on Observables
 * instead of ObservableValues
 *
 * @author eriklark
 */
public class ObservableHelper {

	public static ObservableHelper addListener(ObservableHelper helper, Observable source, InvalidationListener il) {
		if (helper == null) {
			helper = new ObservableHelper(source);
		}

		helper.addListener(il);
		return helper;
	}

	public static ObservableHelper removeListener(ObservableHelper helper, InvalidationListener il) {
		if (helper != null) {
			helper.removeListener(il);
		}

		return helper;
	}

	public static void fireEvent(ObservableHelper helper) {
		helper.fireEvent();
	}

	private final Collection<InvalidationListener> listeners;
	private final Observable source;

	private ObservableHelper(Observable source) {
		this.source = source;
		listeners = new LinkedHashSet<>();
	}

	private void addListener(InvalidationListener listener) {
		listeners.add(listener);
	}

	private void removeListener(InvalidationListener listener) {
		listeners.remove(listener);
	}

	private void fireEvent() {
		listeners.stream().forEach((listener) -> listener.invalidated(source));
	}
}

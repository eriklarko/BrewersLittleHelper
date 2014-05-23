package se.angstroms.blh.anders.uncategorized.value;

import javafx.beans.property.ObjectPropertyBase;
import org.blh.core.unit.Unit;

/**
 * Represents a value that the user have given as input
 *
 * @author thinner
 */
public class InputtedValue<T extends Unit<?>> extends ObjectPropertyBase<T> implements Value<T> {

    public InputtedValue() {
    }

    public InputtedValue(T value) {
        this.set(value);
    }

	@Override
	public Object getBean() {
		return null;
	}

	@Override
	public String getName() {
		return "";
	}
}

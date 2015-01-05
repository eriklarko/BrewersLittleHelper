package se.angstroms.blh.anders.context.value;

import org.blh.core.unit.Unit;

import javafx.beans.property.ObjectPropertyBase;

/**
 * Represents a value that the user have given as input
 *
 * @author thinner
 */
public class InputtedValue<T extends Unit<?>> extends ObjectPropertyBase<T> implements Value<T> {

	private final Id valueType;

    public InputtedValue(Id valueType) {
		this(valueType, null);
    }

    public InputtedValue(Id valueType, T value) {
		this.valueType = valueType;
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

	@Override
	public Id getValueType() {
		return valueType;
	}
}

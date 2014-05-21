package org.blh.formuladecorator;

import javafx.beans.property.ObjectPropertyBase;

/**
 * Represents a value that the user have given as input
 *
 * @author thinner
 */
public class InputtedValue<T> extends ObjectPropertyBase<T> {

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

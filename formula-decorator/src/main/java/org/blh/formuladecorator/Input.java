package org.blh.formuladecorator;

import javafx.beans.property.ReadOnlyObjectPropertyBase;

/**
 * Represents a value that the user have given as input
 *
 * @author thinner
 */
public class Input<T> extends ReadOnlyObjectPropertyBase<T> {

    private final T value;

    public Input(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

	@Override
	public T get() {
		return value();
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

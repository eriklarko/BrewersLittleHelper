package se.angstroms.blh.anders.context.value;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.value.Value.Id;
import se.angstroms.blh.anders.context.value.parsing.ParseException;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;


/**
 * Represents a value that the user have given as input
 *
 * @author thinner
 * @param <T>
 */
public class InputtedValue<T extends Unit<?>> extends Value<T> {

	private final Id valueType;
	private final ObjectProperty<T> prop = new SimpleObjectProperty<>();

    public InputtedValue(Id valueType) {
		this(valueType, null);
    }

    public InputtedValue(Id valueType, T value) {
		this.valueType = valueType;
        prop.set(value);
    }

	@Override
	public Id getValueType() {
		return valueType;
	}

	public void fromString(String raw) throws ParseException {
		T newValue = (T) getValueType().getParser().parse(raw);
		prop.set(newValue);
	}

	public void set(T t) {
		prop.set(t);
	}

	public ObjectProperty<T> getProperty() {
		return prop;
	}

	@Override
	public T get() {
		return prop.get();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		prop.addListener(listener);
	}

	public void addListener(ChangeListener<T> listener) {
		prop.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		prop.removeListener(listener);
	}

	public void removeListener(ChangeListener<T> listener) {
		prop.removeListener(listener);
	}
}

package blh.core.units;

import java.util.Objects;

/**
 *
 * @author thinner
 */
public abstract class Unit<T extends Number> {

    protected T value;

    public Unit(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unit<T> other = (Unit<T>) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
}

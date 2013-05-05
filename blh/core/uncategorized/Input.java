package blh.core.uncategorized;

/**
 *
 * @author thinner
 */
public class Input<T> {
    private T value;

    public Input(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }
}

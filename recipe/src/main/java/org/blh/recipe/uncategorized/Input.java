package org.blh.recipe.uncategorized;

/**
 *
 * @author thinner
 */
public class Input<T> {

    private final T value;

    public Input(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }
}

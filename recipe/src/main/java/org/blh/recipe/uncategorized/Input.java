package org.blh.recipe.uncategorized;

/**
 * Represents a value that the user have given as input
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

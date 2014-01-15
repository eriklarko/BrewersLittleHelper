package org.blh.recipe.uncategorized;

/**
 *
 * @author thinner
 */
public interface Formula<T> {
    public T calc(FullContext context);
}

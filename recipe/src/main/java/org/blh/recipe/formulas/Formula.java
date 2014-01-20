package org.blh.recipe.formulas;

import org.blh.recipe.uncategorized.FullContext;

/**
 * A wrapper class for formulas in the core giving all formulas the same basic
 * method signature.
 *
 * @author Erik Larkö
 */
public interface Formula<T> {

    public T calc(FullContext context);
}

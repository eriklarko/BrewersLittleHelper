package org.blh.recipe.formulas;

import org.blh.core.unit.Unit;
import org.blh.recipe.uncategorized.FullContext;

/**
 * A wrapper class for formulas in the core giving all formulas the same basic
 * method signature.
 *
 * @author Erik Larkö
 * @param <T> The type of unit to calculate.
 */
public interface Formula<T extends Unit<?>> {

    T calc(FullContext context);
}

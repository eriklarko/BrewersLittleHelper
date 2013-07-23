package org.blh.core.formulas;

import org.blh.core.uncategorized.FullContext;

/**
 *
 * @author thinner
 */
public interface Formula<T> {
    public T calc(FullContext context);
}

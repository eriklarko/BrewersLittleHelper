package blh.core.formulas;

import blh.core.uncategorized.FullContext;

/**
 *
 * @author thinner
 */
public interface Formula<T> {
    public T calc(FullContext context);
}

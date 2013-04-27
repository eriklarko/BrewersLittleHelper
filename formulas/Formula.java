package formulas;

import uncategorized.FullContext;

/**
 *
 * @author thinner
 */
public interface Formula<T> {
    public T calc(FullContext context);
}

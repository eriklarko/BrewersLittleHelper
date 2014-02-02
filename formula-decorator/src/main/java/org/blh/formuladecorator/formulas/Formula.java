package org.blh.formuladecorator.formulas;

import org.blh.formuladecorator.FullContext;
import org.blh.core.unit.Unit;

/**
 * A wrapper class for formulas in the core giving all formulas the same basic
 * method signature.
 *
 * @author Erik Larkö
 * @param <T> The type of unit to calculate.
 */
public interface Formula<T extends Unit<?>> {

    T calc(FullContext context);

    String getSomeMathLangRepresentation();
}

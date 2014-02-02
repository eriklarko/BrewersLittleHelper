package org.blh.formuladecorator.formulas;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.blh.core.unit.Unit;
import org.blh.core.unit.bitterness.IBU;
import org.blh.formuladecorator.formulas.decorated.bitterness.DecoratedTinseth;

/**
 * An attempt at dependency injection for formulas.
 *
 * Created by Erik Larkö at 6/28/13 1:28 PM
 */
public final class FormulaFactory {

    private static FormulaFactory instance;
    // Good thing this is private... This is horrific.
    private Map<Object, Object> formulas;

    public static FormulaFactory getInstance() {
        if (instance == null) {
            instance = new FormulaFactory();
        }

        return instance;
    }

    private FormulaFactory() {
        formulas = new HashMap<>();

        formulas.put(IBU.class, Arrays.asList(new DecoratedTinseth()));
    }

    public <T extends Unit<?>> Collection<Formula<T>> getFormulasFor(Class<T> clazz) throws NoFormulaFoundException, NoMatchingFormulaFoundException {
        Object fs = formulas.get(clazz);
        if (fs == null) {
            throw new NoFormulaFoundException("Found no formulas for " + clazz.getSimpleName());
        }

        try {
            return (Collection<Formula<T>>) fs;
        } catch (ClassCastException ex) {
            throw new NoMatchingFormulaFoundException("The formulas for " + clazz.getSimpleName() + " did not match the specified generic type.", ex);
        }
    }
}

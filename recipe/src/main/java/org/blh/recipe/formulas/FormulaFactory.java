package org.blh.recipe.formulas;

import java.util.Map;


/**
 * Created by Erik Larkö at 6/28/13 1:28 PM
 */
public class FormulaFactory {

    private static FormulaFactory instance;
    private Map<Object, Formula<?>> formulas;

    public static FormulaFactory getInstance() {
        if(instance == null) {
            instance = new FormulaFactory();
        }

        return instance;
    }

    private FormulaFactory() {
    }

    public <T> Formula<T> getFormula(Object key) throws NoFormulaFoundException, NoMatchingFormulaFoundException {
        Formula<?> f = formulas.get(key);
        if(f == null) {
            throw new NoFormulaFoundException("Found no formula for key >" + key + "<");
        }

        try {
            return (Formula<T>) f;
        } catch (ClassCastException ex) {
            throw new NoMatchingFormulaFoundException("The formula for key >" + key + "< did not match the specified generic type.", ex);
        }
    }
}

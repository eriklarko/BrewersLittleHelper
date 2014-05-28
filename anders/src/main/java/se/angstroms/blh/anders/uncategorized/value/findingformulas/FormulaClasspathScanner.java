package se.angstroms.blh.anders.uncategorized.value.findingformulas;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.reflections.Reflections;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 * Searches set set of all loaded classes for those classes implementing
 * ObservableFormula and annotated with @Formula. These classes are then used
 * to find which formulas are available when calculcating values of FullContext
 *
 * @author eriklark
 */
public class FormulaClasspathScanner {

    public void findAndAddFormulas(FormulaFactory formulaFactory, FullContext context) throws FormulaFinderException {
        Reflections reflections = new Reflections("");
        Set<Class<? extends ObservableFormula>> observableFormulas = reflections.getSubTypesOf(ObservableFormula.class);

        for (Class<? extends ObservableFormula> c : observableFormulas) {
            if (c.isAnnotationPresent(Formula.class)) {
                try {
                    addFormula(formulaFactory, context, c);
                } catch (Exception ex) {
                    throw new FormulaFinderException("Unable to add " + c, ex);
                }
            }
        }
    }

    private void addFormula(FormulaFactory formulaFactory, FullContext context, Class<? extends ObservableFormula> c) throws IllegalAccessException, SecurityException, NoSuchMethodException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        ObservableFormula formula = c.getConstructor(FullContext.class).newInstance(context);

        Formula annotation = c.getAnnotation(Formula.class);
        for (ValueId valueId : annotation.calculates()) {
            registerFormula(formulaFactory, valueId, formula);
        }
    }

    private void registerFormula(FormulaFactory formulaFactory, ValueId valueId, ObservableFormula formula) {
        if (valueId != ValueId.NOTHING) {
            formulaFactory.register(valueId, formula);
        }
    }

    public static class FormulaFinderException extends Exception {

        public FormulaFinderException(String message) {
            super(message);
        }

        public FormulaFinderException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

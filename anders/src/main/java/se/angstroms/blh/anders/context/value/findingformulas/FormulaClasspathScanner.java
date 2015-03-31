package se.angstroms.blh.anders.context.value.findingformulas;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.blh.core.unit.Unit;
import org.reflections.Reflections;

import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.formulas.volume.AngstromsBiabFormula;

/**
 * Searches set set of all loaded classes for those classes implementing
 * ObservableFormula and annotated with @Formula. These classes are then used to
 * find which formulas are available when calculating values of FullContext
 *
 * @author eriklark
 */
public class FormulaClasspathScanner {

    public void findAndAddFormulas(FormulaDirectory formulaFactory) throws FormulaFinderException {
        Reflections reflections = new Reflections("");
        Set<Class<? extends ObservableFormula<? extends Unit<?>>>> observableFormulas = reflections.getSubTypesOf(ObservableFormula.class);

        // TODO: When I understand the reflections package, I guess I should remove this :)
        observableFormulas.add(AngstromsBiabFormula.class);

        for (Class<? extends ObservableFormula<? extends Unit<?>>> c : observableFormulas) {
            if (c.isAnnotationPresent(Formula.class)) {
                try {
                    addFormula(formulaFactory, c);
                } catch (Exception ex) {
                    throw new FormulaFinderException("Unable to add " + c, ex);
                }
            }
        }
    }

    private void addFormula(FormulaDirectory formulaFactory, Class<? extends ObservableFormula> formulaClass) throws IllegalAccessException, SecurityException, NoSuchMethodException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Formula annotation = formulaClass.getAnnotation(Formula.class);
        for (Value.Id valueId : annotation.calculates()) {
			formulaFactory.register(valueId, formulaClass);
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

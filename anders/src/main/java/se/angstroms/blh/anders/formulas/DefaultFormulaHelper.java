package se.angstroms.blh.anders.formulas;

import java.lang.reflect.Field;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.annot.ValueAnnot;
import se.angstroms.blh.anders.context.value.findingformulas.FormulaDirectory;
import se.angstroms.blh.anders.context.value.findingformulas.NoDefaultFormulaException;

/**
 *
 * @author eriklark
 */
public class DefaultFormulaHelper {

    private final FormulaDirectory formulaFactory;

    public DefaultFormulaHelper(FormulaDirectory formulaFactory) {
        this.formulaFactory = formulaFactory;
    }

    public void setupDefaultFormulas(FullContext context) throws NoDefaultFormulaException {
        for (Field field : context.getClass().getDeclaredFields()) {
            try {
                initializeFieldIfAnnotated(field, context);
            } catch (IllegalAccessException | IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initializeFieldIfAnnotated(Field field, FullContext context) throws IllegalArgumentException, IllegalAccessException, NoDefaultFormulaException {
        if (field.isAnnotationPresent(ValueAnnot.class)) {
            field.setAccessible(true);
            if (field.getType().isAssignableFrom(InputtedOrCalculatedValue.class)) {
                initializeEmptyInputtedOrCalculatedValue(field, context);
            }
        }
    }

    private void initializeEmptyInputtedOrCalculatedValue(Field field, FullContext context) throws IllegalArgumentException, IllegalAccessException, NoDefaultFormulaException {
        InputtedOrCalculatedValue value = (InputtedOrCalculatedValue) field.get(context);

        ObservableFormula<Unit<?>> defaultFormula = findAndBindDefaultFormula(field, context);
        value.setFormula(defaultFormula);
    }

    private ObservableFormula<Unit<?>> findAndBindDefaultFormula(Field field, FullContext context) throws NoDefaultFormulaException {
        ValueAnnot annotation = field.getAnnotation(ValueAnnot.class);
        PartialObservableFormula<Unit<?>> defaultFormula = formulaFactory.getDefaultFormula(annotation.id());
        ObservableFormula<Unit<?>> formula = defaultFormula.bindTo(context);

        return formula;
    }
}

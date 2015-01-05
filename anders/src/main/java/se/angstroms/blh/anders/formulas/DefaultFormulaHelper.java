package se.angstroms.blh.anders.formulas;

import java.lang.reflect.Field;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.Value;
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
                initializeFieldIfValue(field, context);
            } catch (IllegalAccessException | IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initializeFieldIfValue(Field field, FullContext context) throws IllegalArgumentException, IllegalAccessException, NoDefaultFormulaException {
        Object fieldValue = field.get(context);
		
		if (fieldValue instanceof Value) {
            field.setAccessible(true);
            if (field.getType().isAssignableFrom(InputtedOrCalculatedValue.class)) {
                initializeEmptyInputtedOrCalculatedValue((Value) fieldValue, context);
            }
        }
    }

    private void initializeEmptyInputtedOrCalculatedValue(Value value, FullContext context) throws IllegalArgumentException, IllegalAccessException, NoDefaultFormulaException {
        InputtedOrCalculatedValue iocv = (InputtedOrCalculatedValue) value;

        ObservableFormula<Unit<?>> defaultFormula = findAndBindDefaultFormula(value, context);
        iocv.setFormula(defaultFormula);
    }

    private ObservableFormula<Unit<?>> findAndBindDefaultFormula(Value value, FullContext context) throws NoDefaultFormulaException {
        PartialObservableFormula<Unit<?>> defaultFormula = formulaFactory.getDefaultFormula(value.getValueType());
        ObservableFormula<Unit<?>> formula = defaultFormula.bindTo(context);

        return formula;
    }
}

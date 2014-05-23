package se.angstroms.blh.anders.uncategorized.value.annot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.FormulaFactory;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.NoDefaultFormulaException;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.Value;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 * Builds an index of ValueIds to variables in the FullContext
 *
 * @author eriklark
 */
public class InputtedOrCalculatedValueIndex {

    @Inject
    private FormulaFactory formulaFactory;

    private final Map<ValueId, Value<? extends Unit<?>>> map;

    public InputtedOrCalculatedValueIndex() {
        map = new HashMap<>();
    }

    public void buildIndex(FullContext context) throws ValueMappingException {
        for (Field field : context.getClass().getDeclaredFields()) {
            try {
                if (field.isAnnotationPresent(ValueAnnot.class)) {
                    tryToBindField(field, context);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new ValueMappingException("Could not access field " + field.getName(), ex);
            }
        }
    }

    private void tryToBindField(Field field, FullContext context) throws IllegalArgumentException, IllegalAccessException, ValueMappingException {
        field.setAccessible(true);
        Object fieldValue = field.get(context);

        if (fieldValue instanceof InputtedOrCalculatedValue) {
            ValueAnnot annotation = field.getAnnotation(ValueAnnot.class);
            Value<? extends Unit<?>> oldValueAsValue = map.putIfAbsent(annotation.id(), (InputtedOrCalculatedValue<? extends Unit<?>>) fieldValue);
            InputtedOrCalculatedValue<? extends Unit<?>> oldValue = (InputtedOrCalculatedValue<? extends Unit<?>>) oldValueAsValue;

            if (oldValue != null) {
                throw new ValueMappingException("Failed to add " + field.getName() + ", " + annotation.id() + " was already associated with " + map.get(annotation.id()));
            }
        } else {
            throw new ValueMappingException(field.getName() + " is not a valid target for @Value");
        }
    }

    public <E extends Unit<?>> InputtedOrCalculatedValue<E> fromDefaultFormula(ValueId type) throws NoDefaultFormulaException {
        InputtedOrCalculatedValue<E> value = (InputtedOrCalculatedValue<E>) map.get(type);
        if (value == null) {
            ObservableFormula<E> defaultFormula = formulaFactory.getDefaultFormula(type);
            value = new InputtedOrCalculatedValue<>(defaultFormula);
        }

        return value;
    }
}

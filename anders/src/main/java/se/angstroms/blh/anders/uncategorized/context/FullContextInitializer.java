package se.angstroms.blh.anders.uncategorized.context;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.blh.core.unit.Factor;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.NopFormula;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.InputtedValue;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.uncategorized.value.annot.Value;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.FormulaFactory;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.NoDefaultFormulaException;

/**
 *
 * @author eriklark
 */
public class FullContextInitializer {

    public static void main(String[] args) throws InitializerException {
        FullContext fullContext = new FullContext();

        FullContextInitializer fullContextInitializer = new FullContextInitializer();
        fullContextInitializer.formulaFactory = new FormulaFactory();
        fullContextInitializer.formulaFactory.register(ValueId.OG, new NopFormula<>(new Factor(1), fullContext));
        fullContextInitializer.formulaFactory.register(ValueId.EXTRACTION_EFFICIENCY, new NopFormula<>(new Factor(2), fullContext));
        fullContextInitializer.initializeMeEmpty(fullContext);

        System.out.println("EE: " + fullContext.getExtractionEfficiency().value());
        System.out.println("OG: " + fullContext.getOriginalGravity().value());
    }

    @Inject
    private FormulaFactory formulaFactory;

    private final Map<Class<?>, Initializer> initializers;

    public FullContextInitializer() {
        initializers = new HashMap<>();

        initializers.put(InputtedOrCalculatedValue.class, this::initializeEmptyInputtedOrCalculatedValue);
        initializers.put(InputtedValue.class, this::initializeEmptyInputtedValue);
        initializers.put(Equipment.class, this::initializeEmptyEquipment);
        initializers.put(GeneralBreweryInfo.class, this::initializeEmptyGeneralBreweryInfo);
    }

    public void initializeMeEmpty(FullContext context) throws InitializerException {
        for (Field field : context.getClass().getDeclaredFields()) {
            try {
                if (field.isAnnotationPresent(Value.class)) {

                    Initializer initializer = initializers.get(field.getType());
                    if (initializer == null) {
                        throw new InitializerException(field.getName() + " IS NOT INITIALIZABLE. I dåånt know haow");
                    } else {
                        field.setAccessible(true);
                        initializer.initialize(field, context);
                    }
                }
            } catch (Exception ex) {
                throw new InitializerException("Unable to initialize " + field.getName(), ex);
            }
        }
    }

    private void initializeEmptyInputtedOrCalculatedValue(Field field, FullContext context) throws IllegalArgumentException, IllegalAccessException, NoDefaultFormulaException {
        Value annotation = field.getAnnotation(Value.class);
        ObservableFormula<? extends Unit<?>> formula = formulaFactory.getDefaultFormula(annotation.id());
        InputtedOrCalculatedValue value = (InputtedOrCalculatedValue) field.get(context);
        value.setFormula(formula);
    }

    private void initializeEmptyInputtedValue(Field field, FullContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initializeEmptyEquipment(Field field, FullContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initializeEmptyGeneralBreweryInfo(Field field, FullContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @FunctionalInterface
    private interface Initializer {

        void initialize(Field field, FullContext context) throws IllegalArgumentException, IllegalAccessException, Exception;
    }
}

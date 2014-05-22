package se.angstroms.blh.anders.uncategorized.iocv;

import java.util.HashMap;
import java.util.Map;
import se.angstroms.blh.anders.uncategorized.iocv.findingformulas.FormulaFactory;
import se.angstroms.blh.anders.uncategorized.iocv.findingformulas.NoDefaultFormulaException;
import javax.inject.Inject;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.uncategorized.FullContext;
import se.angstroms.blh.anders.formulas.ObservableFormula;

/**
 * Mappar upp IOCVs till ValuePresenters och FullContext
 *
 * @author eriklark
 */
public class InputtedOrCalculatedValueFactory {

    @Inject
    private FullContext context;

    @Inject
    private FormulaFactory formulaFactory;

    private final Map<ValueId, InputtedOrCalculatedValue<? extends Unit<?>>> map;

    public InputtedOrCalculatedValueFactory() {
        map = new HashMap<>();

        map.put(ValueId.OG, context.getOriginalGravity());
        map.put(ValueId.EXTRACTION_EFFICIENCY, context.getExtractionEfficiency());
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

package se.angstroms.blh.anders.uncategorized;

import javax.inject.Inject;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import org.blh.formuladecorator.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.ValueId;

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

    public <E extends Unit<?>> InputtedOrCalculatedValue<E> fromDefaultFormula(ValueId type) throws NoDefaultFormulaException {

        switch (type) {
            case OG:
                return (InputtedOrCalculatedValue<E>) context.getOriginalGravity();
            case EXTRACTION_EFFICIENCY:
                return (InputtedOrCalculatedValue<E>) context.getExtractionEfficiency();
            default:
                ObservableFormula<E> defaultFormula = formulaFactory.getDefaultFormula(type);
                return new InputtedOrCalculatedValue<>(defaultFormula);
        }
    }
}

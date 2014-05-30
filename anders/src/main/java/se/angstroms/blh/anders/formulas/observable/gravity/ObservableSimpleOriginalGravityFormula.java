
package se.angstroms.blh.anders.formulas.observable.gravity;

import se.angstroms.blh.anders.uncategorized.context.FullContext;
import org.blh.core.formula.gravity.originalgravity.SimpleOriginalGravityFormula;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.volume.Liters;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.Formula;

/**
 *
 * @author Thinner
 */
@Formula(calculates = ValueId.OG)
public class ObservableSimpleOriginalGravityFormula extends ObservableFormula<SpecificGravity> {

	private final SimpleOriginalGravityFormula f;

	public ObservableSimpleOriginalGravityFormula(FullContext context) {
		super(context);

		f = new SimpleOriginalGravityFormula();
	}

	@Override
	protected void registerDependentVariables(FullContext context) {
		registerDependentVariable(context.getExtractionEfficiency());
		registerDependentVariable(context.getIngredientsList().getFermentables());
	}

    @Override
    public SpecificGravity calc() {
		System.out.println("Calculating OG");
        return f.calc(getContext().getIngredientsList().getFermentables(), new Liters(12), getContext().getExtractionEfficiency().get());
    }
}

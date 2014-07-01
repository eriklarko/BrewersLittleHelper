
package se.angstroms.blh.anders.formulas.bitterness;

import se.angstroms.blh.anders.context.FullContext;
import org.blh.core.formula.bitterness.Tinseth;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.volume.Liters;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;

/**
 *
 * @author Thinner
 */
@Formula(calculates = Value.Id.BITTERNESS)
public class ObservableTinseth extends ObservableFormula<IBU> {

	Tinseth tinseth = new Tinseth();

	public ObservableTinseth(FullContext context) {
		super(context);
	}

	@Override
	protected void registerDependentVariables(FullContext context) {
		registerDependentVariable(context.getIngredientsList().getHopAdditions());
		registerDependentVariable(context.getOriginalGravity());
	}

	@Override
	public IBU calc() {
		System.out.println("Calculating tinseth");
		return tinseth.calc(getContext().getIngredientsList().getHopAdditions(), new Liters(12), getContext().getOriginalGravity().get());
	}
}

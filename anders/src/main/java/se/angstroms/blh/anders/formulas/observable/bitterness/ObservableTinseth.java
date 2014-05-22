
package se.angstroms.blh.anders.formulas.observable.bitterness;

import se.angstroms.blh.anders.uncategorized.context.FullContext;
import org.blh.core.formula.bitterness.Tinseth;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.volume.Liters;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.Formula;

/**
 *
 * @author Thinner
 */
@Formula(calculates = ValueId.BITTERNESS)
public class ObservableTinseth extends ObservableFormula<IBU> {

	Tinseth tinseth = new Tinseth();

	public ObservableTinseth(FullContext context) {
		super(context);
	}

	@Override
	protected void registerDependentVariables(FullContext context) {
		registerDependentVariable(context.recipeProperty());
		registerDependentVariable(context.getOriginalGravity());
	}

	@Override
	public IBU calc() {
		System.out.println("Calculating tinseth");
		return tinseth.calc(getContext().getRecipe().getIngredientsList().getHopAdditions(), new Liters(12), getContext().getOriginalGravity().value());
	}

	@Override
	public String getSomeMathLangRepresentation() {
		return "a + b";
	}
}

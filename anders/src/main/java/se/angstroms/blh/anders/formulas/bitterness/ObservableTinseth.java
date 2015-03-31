
package se.angstroms.blh.anders.formulas.bitterness;

import java.util.Set;

import org.blh.core.formula.bitterness.Tinseth;
import org.blh.core.unit.bitterness.IBU;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;
import se.angstroms.blh.anders.formulas.ObservableFormula;

import com.google.common.collect.Sets;

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
		registerDependentVariable(context.getIngredientsList().getHopAdditions(), Value.Id.HOP_ADDITIONS);
		registerDependentVariable(context.getPostBoilVolume());
		registerDependentVariable(context.getOriginalGravity());
	}

	@Override
	public IBU calc() {
		System.out.println("Calculating tinseth");
		return tinseth.calc(getContext().getIngredientsList().getHopAdditions(), getContext().getPostBoilVolume().get(), getContext().getOriginalGravity().get());
	}

	@Override
	public String getName() {
		return "Tinseth";
	}

	@Override
	public String getMathRepresentation() {
		return "1+1";
	}

	@Override
	public String getDescription() {
		return "The formula used in Beersmith 2";
	}

	@Override
	public Set<Value.Id> calculates() {
		return Sets.immutableEnumSet(Value.Id.BITTERNESS);
	}
}

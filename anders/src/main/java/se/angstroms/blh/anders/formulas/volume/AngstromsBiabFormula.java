package se.angstroms.blh.anders.formulas.volume;

import java.util.Set;

import org.blh.core.unit.volume.Liters;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;
import se.angstroms.blh.anders.formulas.ObservableFormula;

import com.google.common.collect.Sets;

/**
 *
 * @author eriklark
 */

@Formula(calculates = Value.Id.PRE_MASH_VOLUME)
public class AngstromsBiabFormula extends ObservableFormula<Liters>{

    public AngstromsBiabFormula(FullContext context) {
        super(context);
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
        registerDependentVariable(context.getPostBoilVolume());
        registerDependentVariable(context.getIngredientsList().getFermentables(), Value.Id.FERMENTABLES);
    }

    @Override
    public Liters calc() {
        double boilOff = 2;
        double trubLoss = 0.5;
        double batchSize = getContext().getPostBoilVolume().get().value();
        double maltWeight = getContext().getIngredientsList().getFermentables().stream().map((gp) -> gp.getAmount().value()).reduce(0d, (pre, toAdd) -> pre + toAdd);

        return new Liters(boilOff + trubLoss + batchSize + maltWeight * 0.9);
    }

	@Override
	public String getName() {
		return "LE BIAB FORMULA OF DOOM";
	}

	@Override
	public String getMathRepresentation() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getDescription() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Set<Value.Id> calculates() {
		return Sets.immutableEnumSet(Value.Id.PRE_MASH_VOLUME);
	}
}

package se.angstroms.blh.anders.formulas.volume;

import org.blh.core.unit.volume.Liters;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;
import se.angstroms.blh.anders.formulas.ObservableFormula;

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
        registerDependentVariable(context.getIngredientsList().getFermentables());
    }

    @Override
    public Liters calc() {
        double boilOff = 2;
        double trubLoss = 0.5;
        double batchSize = getContext().getPostBoilVolume().get().value();
        double maltWeight = getContext().getIngredientsList().getFermentablesSnapshot().stream().map((gp) -> gp.getAmount().value()).reduce(0d, (pre, toAdd) -> pre + toAdd);

        return new Liters(boilOff + trubLoss + batchSize + maltWeight * 0.9);
    }
}

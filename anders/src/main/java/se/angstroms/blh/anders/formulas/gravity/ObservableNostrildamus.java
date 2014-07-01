package se.angstroms.blh.anders.formulas.gravity;

import org.blh.core.formula.gravity.finalgravity.Nostrildamus;
import org.blh.core.unit.gravity.SpecificGravity;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;

@Formula(calculates = Value.Id.FG)
public class ObservableNostrildamus extends ObservableFormula<SpecificGravity> {

    private final Nostrildamus f;

    public ObservableNostrildamus(FullContext context) {
        super(context);
        this.f = new Nostrildamus();
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
        registerDependentVariable(context.getOriginalGravity());
        registerDependentVariable(context.getYeastApparentAttenuation());
    }

    @Override
    public SpecificGravity calc() {
        System.out.println("Calculating FG");
        return f.calc(getContext().getOriginalGravity().get(), getContext().getYeastApparentAttenuation().get().asFactor());
    }
}

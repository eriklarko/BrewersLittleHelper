package se.angstroms.blh.anders.formulas.observable.gravity;

import org.blh.core.formula.gravity.finalgravity.Nostrildamus;
import org.blh.core.unit.gravity.SpecificGravity;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.Formula;

@Formula(calculates = ValueId.FG)
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
        return f.calc(getContext().getOriginalGravity().get(), getContext().getYeastApparentAttenuation().get());
    }

    @Override
    public String getSomeMathLangRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

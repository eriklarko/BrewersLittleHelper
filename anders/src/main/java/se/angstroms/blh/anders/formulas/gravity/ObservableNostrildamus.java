package se.angstroms.blh.anders.formulas.gravity;

import java.util.Set;

import org.blh.core.formula.gravity.finalgravity.Nostrildamus;
import org.blh.core.unit.gravity.SpecificGravity;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;
import se.angstroms.blh.anders.formulas.ObservableFormula;

import com.google.common.collect.Sets;

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

	@Override
	public String getName() {
		return "Nostrildamus";
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
		return Sets.immutableEnumSet(Value.Id.FG);
	}
}

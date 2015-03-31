package se.angstroms.blh.anders.formulas.alcohol;

import java.util.Set;

import org.blh.core.formula.alcohol.Daniels;
import org.blh.core.unit.alcohol.ABV;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;
import se.angstroms.blh.anders.formulas.ObservableFormula;

import com.google.common.collect.Sets;

@Formula(calculates = Value.Id.ALCOHOL_CONTENT)
public class ObservableDaniels extends ObservableFormula<ABV> {

    private final Daniels f;

    public ObservableDaniels(FullContext context) {
        super(context);
        this.f = new Daniels();
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
        registerDependentVariable(context.getOriginalGravity());
        registerDependentVariable(context.getFinalGravity());
    }

    @Override
    public ABV calc() {
        System.out.println("Calculating ABV");
        return f.calc(getContext().getOriginalGravity().get(), getContext().getFinalGravity().get());
    }

	@Override
	public String getName() {
		return "Daniels";
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
		return Sets.immutableEnumSet(Value.Id.ALCOHOL_CONTENT);
	}
}

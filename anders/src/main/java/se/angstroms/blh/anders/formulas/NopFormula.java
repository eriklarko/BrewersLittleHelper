package se.angstroms.blh.anders.formulas;

import java.util.Set;

import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;

import com.google.common.collect.Sets;

@Formula(calculates = Value.Id.NOTHING)
public class NopFormula<T extends Unit<?>> extends ObservableFormula<T> {

    private final T val;

    public NopFormula(FullContext context) {
        this(null, context);
    }

    public NopFormula(T val, FullContext context) {
        super(context);
        this.val = val;
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
    }

    @Override
    public T calc() {
        return val;
    }

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
		return Sets.immutableEnumSet(Value.Id.NOTHING);
	}
}

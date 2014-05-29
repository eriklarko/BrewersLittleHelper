package se.angstroms.blh.anders.formulas.observable.alcohol;

import org.blh.core.formula.alcohol.Daniels;
import org.blh.core.unit.alcohol.ABV;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.value.ValueId;
import se.angstroms.blh.anders.uncategorized.value.findingformulas.Formula;

@Formula(calculates = ValueId.ALCOHOL_CONTENT)
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
    public String getSomeMathLangRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

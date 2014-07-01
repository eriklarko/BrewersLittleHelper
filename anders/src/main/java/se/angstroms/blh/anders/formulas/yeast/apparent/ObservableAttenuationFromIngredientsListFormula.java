package se.angstroms.blh.anders.formulas.yeast.apparent;

import java.util.ArrayList;
import java.util.Collection;
import org.blh.core.formula.yeast.attenuation.apparent.SummedFromIngredientsList;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.volume.Milliliters;
import org.blh.core.unit.weight.Grams;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.context.value.findingformulas.Formula;

/**
 *
 * @author eriklark
 */
@Formula(calculates = Value.Id.YEAST_ATTENUATION)
public class ObservableAttenuationFromIngredientsListFormula extends ObservableFormula<Percentage> {

    private final SummedFromIngredientsList formula = new SummedFromIngredientsList();

    public ObservableAttenuationFromIngredientsListFormula(FullContext context) {
        super(context);
    }

    @Override
    protected void registerDependentVariables(FullContext context) {
        registerDependentVariable(context.getIngredientsList().getYeastAdditions());
    }

    @Override
    public Percentage calc() {
        Collection<YeastAddition<Grams>> grams = new ArrayList<>();
        Collection<YeastAddition<Milliliters>> millilitres = new ArrayList<>();
        for (YeastAddition<?> yeastAddition : getContext().getIngredientsList().getYeastAdditions()) {
            if (yeastAddition.getAmount() instanceof Grams) {
                grams.add((YeastAddition<Grams>) yeastAddition);
            } else if (yeastAddition.getAmount() instanceof Milliliters) {
                millilitres.add((YeastAddition<Milliliters>) yeastAddition);
            }
        }

        if (grams.isEmpty()) {
            return formula.calcLiquid(millilitres);
        }

        return formula.calcDry(grams);
    }
}

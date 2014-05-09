
package org.blh.formuladecorator.formulas.observable.gravity;

import org.blh.formuladecorator.FullContext;
import org.blh.core.formula.gravity.originalgravity.SimpleOriginalGravityFormula;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.volume.Liters;
import org.blh.formuladecorator.formulas.ObservableFormula;

/**
 *
 * @author Thinner
 */
public class ObservableSimpleOriginalGravityFormula extends ObservableFormula<SpecificGravity> {

	private final SimpleOriginalGravityFormula f;

	public ObservableSimpleOriginalGravityFormula(FullContext context) {
		super(context);

		f = new SimpleOriginalGravityFormula();
	}

	@Override
	protected void registerDependentVariables(FullContext context) {
		registerDependentVariable(context.getExtractionEfficiency());
		registerDependentVariable(context.recipeProperty());
	}

    @Override
    public SpecificGravity calc() {
		System.out.println("Calculating OG");
        return f.calc(getContext().getRecipe().getIngredientsList().getFermentables(), new Liters(12), getContext().getExtractionEfficiency().value());
    }

    @Override
    public String getSomeMathLangRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

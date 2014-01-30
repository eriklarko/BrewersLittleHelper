
package org.blh.formulas.decorated.gravity;

import org.blh.FullContext;
import org.blh.core.formula.gravity.originalgravity.SimpleOriginalGravityFormula;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.volume.Liters;
import org.blh.formulas.Formula;

/**
 *
 * @author Thinner
 */
public class DecoratedSimpleOriginalGravityFormula extends SimpleOriginalGravityFormula implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        return this.calc(context.getRecipe().getIngredientsList().getFermentables(), new Liters(12), context.getExtractionEfficiency().value());
    }

    @Override
    public String getSomeMathLangRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

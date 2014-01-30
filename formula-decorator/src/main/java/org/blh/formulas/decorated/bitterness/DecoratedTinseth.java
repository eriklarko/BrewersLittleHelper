
package org.blh.formulas.decorated.bitterness;

import org.blh.FullContext;
import org.blh.core.formula.bitterness.Tinseth;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.volume.Liters;
import org.blh.formulas.Formula;

/**
 *
 * @author Thinner
 */
public class DecoratedTinseth extends Tinseth implements Formula<IBU> {

    @Override
    public IBU calc(FullContext context) {
        return this.calc(context.getRecipe().getIngredientsList().getHopAdditions(), new Liters(12), context.getOriginalGravity().value());
    }

    @Override
    public String getSomeMathLangRepresentation() {
        return "a + b";
    }

}

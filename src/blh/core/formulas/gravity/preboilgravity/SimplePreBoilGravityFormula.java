package blh.core.formulas.gravity.preboilgravity;

import blh.core.formulas.gravity.originalgravity.SimpleOriginalGravityFormula;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class SimplePreBoilGravityFormula extends SimpleOriginalGravityFormula{

    @Override
    public Liters getVolume(FullContext context) {
        return context.preBoilVolume.value();
    }
}

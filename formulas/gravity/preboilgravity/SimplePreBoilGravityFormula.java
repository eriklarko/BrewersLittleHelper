package formulas.gravity.preboilgravity;

import formulas.gravity.originalgravity.SimpleOriginalGravityFormula;
import uncategorized.FullContext;
import units.volume.Liters;

/**
 *
 * @author thinner
 */
public class SimplePreBoilGravityFormula extends SimpleOriginalGravityFormula{

    @Override
    public Liters getVolume(FullContext context) {
        return context.getPreBoilVolume();
    }
}

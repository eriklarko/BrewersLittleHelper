package blh.core.formulas.volumes;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class PostCooling implements Formula<Liters> {

    @Override
    public Liters calc(FullContext context) {
        return new Liters(context.postBoilVolume.value().value() * context.coolingLoss.value().value());
    }
}

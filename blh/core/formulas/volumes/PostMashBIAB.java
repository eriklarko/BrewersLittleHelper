package blh.core.formulas.volumes;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class PostMashBIAB implements Formula<Liters> {

    @Override
    public Liters calc(FullContext context) {
        double waterLeftInGrist = context.totalGrainWeight.value().value() * 0.9;

        return new Liters(context.preMashVolume.value().value() - waterLeftInGrist);
    }
}

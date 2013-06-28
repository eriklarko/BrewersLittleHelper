package blh.core.formulas.volumes;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class PostMashBIAB implements Formula<Liters> {

    public static final double FACTOR_WATER_LEFT_IN_GRAIN = 0.9;

    @Override
    public Liters calc(FullContext context) {
        double waterLeftInGrist = context.totalGrainWeight.value().value() * FACTOR_WATER_LEFT_IN_GRAIN;

        return new Liters(context.preMashVolume.value().value() - waterLeftInGrist);
    }
}

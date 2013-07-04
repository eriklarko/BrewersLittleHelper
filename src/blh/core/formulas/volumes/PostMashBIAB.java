package blh.core.formulas.volumes;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class PostMashBIAB implements VolumeStepFormula {

    public static final double FACTOR_WATER_LEFT_IN_GRAIN = 0.9;

    @Override
    public Liters postStep(Liters preStep, FullContext context) {
        Kilograms totalGrainWeight = context.totalGrainWeight.value();

        return calc(preStep, totalGrainWeight);
    }

    public Liters calc(Liters preMashVolume, Kilograms totalGrainWeight) {
        double waterLeftInGrist = totalGrainWeight.value() * FACTOR_WATER_LEFT_IN_GRAIN;

        return new Liters(preMashVolume.value() - waterLeftInGrist);
    }
}

package blh.core.formulas.volumes;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class FinalVolume implements VolumeStepFormula {

    @Override
    public Liters postStep(Liters preStep, FullContext context) {
        Liters trubLoss = context.trubLoss.value();

        return calc(preStep, trubLoss);
    }

    public Liters calc(Liters postCoolingVolume, Liters trubLoss) {
        return new Liters(postCoolingVolume.value() - trubLoss.value());
    }
}

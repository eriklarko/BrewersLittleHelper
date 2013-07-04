package blh.core.formulas.volumes;

import blh.core.uncategorized.FullContext;
import blh.core.units.Factor;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class PostCooling implements VolumeStepFormula {

    @Override
    public Liters postStep(Liters preStep, FullContext context) {
        Factor coolingLoss = context.coolingLoss.value();

        return calc(preStep, coolingLoss);
    }

    public Liters calc(Liters postBoilVolume, Factor coolingLoss) {
       return new Liters(postBoilVolume.value() * coolingLoss.value());
    }
}

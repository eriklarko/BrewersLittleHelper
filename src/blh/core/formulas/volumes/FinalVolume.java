package blh.core.formulas.volumes;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class FinalVolume implements Formula<Liters> {

    @Override
    public Liters calc(FullContext context) {
        return new Liters(context.postCoolingVolume.value().value() - context.trubLoss.value().value());
    }
}

package blh.core.formulas.volumes.water.impl;

import blh.core.formulas.volumes.water.impl.generic.AdditiveStep;
import blh.core.uncategorized.FullContext;
import blh.core.units.time.Hour;

/**
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:17:11 PM
 */
public class BoilStep extends AdditiveStep {

    public BoilStep() {
        super(Volume.DECREASES_IN_STEP);
    }

    @Override
    protected double term(FullContext context) {
        Hour boilTime = new Hour(context.boilTime.value().value());
        double totalBoilOff = context.getEquipment().getBoilOff().value().value() * boilTime.value();
        
        return totalBoilOff;
    }
}

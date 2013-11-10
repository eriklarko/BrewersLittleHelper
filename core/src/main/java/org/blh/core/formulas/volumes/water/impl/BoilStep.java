package org.blh.core.formulas.volumes.water.impl;

import org.blh.core.formulas.volumes.water.impl.generic.AdditiveStep;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.time.Hour;
import org.blh.core.units.volume.Liters;

/**
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:17:11 PM
 */
public class BoilStep extends AdditiveStep {

    public BoilStep() {
        super(Volume.DECREASES_IN_STEP);
    }

    public BoilStep(Liters measuredVolume) {
        super(Volume.DECREASES_IN_STEP, measuredVolume);
    }

    @Override
    protected double term(FullContext context) {
        Hour boilTime = new Hour(context.boilTime.value().inexactValue());
        double totalBoilOff = context.getEquipment().getBoilOff().value().inexactValue() * boilTime.inexactValue();
        
        return totalBoilOff;
    }
}

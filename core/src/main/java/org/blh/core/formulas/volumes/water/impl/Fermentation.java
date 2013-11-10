package org.blh.core.formulas.volumes.water.impl;

import org.blh.core.formulas.volumes.water.impl.generic.AdditiveStep;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:45:03 PM
 */
public class Fermentation extends AdditiveStep {

    public Fermentation() {
        super(Volume.DECREASES_IN_STEP);
    }

    public Fermentation(Liters measuredVolume) {
        super(Volume.DECREASES_IN_STEP, measuredVolume);
    }

    @Override
    protected double term(FullContext context) {
        return context.getEquipment().getTrubLoss().inexactValue();
    }
}

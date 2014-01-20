package org.blh.recipe.volumes.water.impl;

import org.blh.core.unit.time.Hour;
import org.blh.core.unit.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;
import org.blh.recipe.volumes.water.impl.generic.AdditiveStep;

/**
 * Represent the effect boiling the wort has on water volume.
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
        Hour boilTime = new Hour(context.getBoilTime().value().value());
        double totalBoilOff = context.getEquipment().getBoilOff().value().value() * boilTime.value();

        return totalBoilOff;
    }
}

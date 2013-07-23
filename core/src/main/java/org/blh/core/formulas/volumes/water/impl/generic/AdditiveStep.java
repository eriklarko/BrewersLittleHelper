package org.blh.core.formulas.volumes.water.impl.generic;

import org.blh.core.formulas.volumes.water.BrewStep;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 15, 2013 8:39:55 PM
 */
public abstract class AdditiveStep extends BrewStep {

    public static enum Volume {

        DECREASES_IN_STEP, INCREASES_IN_STEP
    }

    private final Volume vol;

    public AdditiveStep(Volume vol) {
        this.vol = vol;
    }
    
    public AdditiveStep(Volume vol, Liters measuredVolume) {
        super(measuredVolume);
        this.vol = vol;
    }

    protected abstract double term(FullContext context);

    @Override
    protected Liters calculateVolumeAfterStep(Liters base, FullContext context) {
        if (vol == Volume.DECREASES_IN_STEP) {
            return new Liters(base.value() - term(context));
        } else {
            return new Liters(base.value() + term(context));
        }
    }

    @Override
    protected Liters calculateVolumeBeforeStep(Liters base, FullContext context) {
        if (vol == Volume.DECREASES_IN_STEP) {
            return new Liters(base.value() + term(context));
        } else {
            return new Liters(base.value() - term(context));
        }
    }
}

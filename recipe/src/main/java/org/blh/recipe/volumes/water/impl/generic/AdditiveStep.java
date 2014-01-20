package org.blh.recipe.volumes.water.impl.generic;

import org.blh.core.unit.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;
import org.blh.recipe.volumes.water.BrewStep;

/**
 * A generic brew step used by steps whose water effect is calculated by a
 * simple addition or subtraction.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 15, 2013 8:39:55 PM
 */
public abstract class AdditiveStep extends BrewStep {

    /**
     * Determines if the volume increases or decreases in this step.
     */
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

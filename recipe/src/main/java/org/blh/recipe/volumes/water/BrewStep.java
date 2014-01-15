package org.blh.recipe.volumes.water;

import org.blh.core.units.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public abstract class BrewStep {

    private Liters measuredLiters;

    public BrewStep() {
    }

    public BrewStep(Liters measuredLiters) {
        if(measuredLiters == null) {
            throw new NullPointerException("The measured liters cannot be null");
        }

        this.measuredLiters = measuredLiters;
    }

    public boolean isMeasured() {
        return measuredLiters != null;
    }

    public Liters getMeasuredLiters() {
        return measuredLiters;
    }

    protected abstract Liters calculateVolumeAfterStep(Liters volumeBeforeStep, FullContext context);

    protected abstract Liters calculateVolumeBeforeStep(Liters volumeAfterStep, FullContext context);
}

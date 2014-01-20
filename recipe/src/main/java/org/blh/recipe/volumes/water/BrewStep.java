package org.blh.recipe.volumes.water;

import org.blh.core.unit.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;

/**
 * Represent the effect on water volume in each brew step, such as lautering,
 * boiling etc..
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public abstract class BrewStep {

    private Liters measuredLiters;

    public BrewStep() {
    }

    public BrewStep(Liters measuredLiters) {
        if (measuredLiters == null) {
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

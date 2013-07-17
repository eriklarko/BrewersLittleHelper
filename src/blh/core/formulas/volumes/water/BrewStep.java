package blh.core.formulas.volumes.water;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

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

    protected abstract Liters forward(Liters volumeBeforeStep, FullContext context);
    
    protected abstract Liters backward(Liters volumeAfterStep, FullContext context);
}

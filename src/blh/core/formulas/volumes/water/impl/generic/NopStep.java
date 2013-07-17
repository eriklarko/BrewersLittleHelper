package blh.core.formulas.volumes.water.impl.generic;

import blh.core.formulas.volumes.water.BrewStep;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:13:21 PM
 */
public class NopStep extends BrewStep {

    @Override
    protected Liters calculateVolumeAfterStep(Liters volumeBeforeStep, FullContext context) {
        return volumeBeforeStep;
    }

    @Override
    protected Liters calculateVolumeBeforeStep(Liters volumeAfterStep, FullContext context) {
        return volumeAfterStep;
    }

}

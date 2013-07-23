package org.blh.core.formulas.volumes.water.impl.generic;

import org.blh.core.formulas.volumes.water.BrewStep;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;

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

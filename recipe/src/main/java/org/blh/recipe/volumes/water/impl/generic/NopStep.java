package org.blh.recipe.volumes.water.impl.generic;

import org.blh.core.unit.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;
import org.blh.recipe.volumes.water.BrewStep;

/**
 * A step that doesn't affect water volume. Used to denote start and final
 * volumes.
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

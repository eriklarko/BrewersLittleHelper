package org.blh.recipe.volumes.water.impl.generic;

import org.blh.recipe.volumes.water.BrewStep;
import org.blh.core.units.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 15, 2013 8:39:55 PM
 */
public class MultiplicativeStep extends BrewStep {

    private final double term;

    public MultiplicativeStep(double term) {
        this.term = term;
    }

    @Override
    protected Liters calculateVolumeAfterStep(Liters base, FullContext context) {
        return new Liters(base.value() * term);
    }

    @Override
    protected Liters calculateVolumeBeforeStep(Liters base, FullContext context) {
        return new Liters(base.value() / term);
    }
}

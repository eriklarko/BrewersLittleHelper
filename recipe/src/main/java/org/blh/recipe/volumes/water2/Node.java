package org.blh.recipe.volumes.water2;

import org.blh.core.units.volume.Liters;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 22, 2013 9:46:56 PM
 */
public interface Node extends ForwardChange, BackwardChange {

    void measure(Liters volume);

    boolean isMeasured();
}

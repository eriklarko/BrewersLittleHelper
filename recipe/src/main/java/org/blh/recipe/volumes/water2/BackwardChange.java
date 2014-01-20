package org.blh.recipe.volumes.water2;

import org.blh.core.unit.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;

/**
 * Used to calculate the water volume backwards.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 22, 2013 9:45:41 PM
 */
public interface BackwardChange {

    Liters calcBackwards(Liters volumeAfterStep, FullContext context);
}

package org.blh.recipe.volumes.water2;

import org.blh.core.units.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 22, 2013 9:45:29 PM
 */
public interface ForwardChange {

    Liters calcForwards(Liters volumeBeforeStep, FullContext context);
}

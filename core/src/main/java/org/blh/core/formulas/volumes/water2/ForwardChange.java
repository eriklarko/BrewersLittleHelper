
package org.blh.core.formulas.volumes.water2;

import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 22, 2013 9:45:29 PM
 */
public interface ForwardChange {

    Liters calcForwards(Liters volumeBeforeStep, FullContext context);
}

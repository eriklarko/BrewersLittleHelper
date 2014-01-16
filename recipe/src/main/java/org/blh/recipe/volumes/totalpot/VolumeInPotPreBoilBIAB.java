package org.blh.recipe.volumes.totalpot;

import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Kilograms;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:08:37 PM
 */
public class VolumeInPotPreBoilBIAB {

    private static final double GRAIN_VOLUME = 1;

    public Liters calc(Kilograms grainWeight, Liters preMashVolume) {
        return new Liters(grainWeight.value() * GRAIN_VOLUME + preMashVolume.value());
    }

}

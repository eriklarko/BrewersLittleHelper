package org.blh.core.formula.volumes.totalpot;

import org.blh.core.unit.Factor;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Kilograms;

/**
 * Calculates how much volume is in the pot before boiling when using BIAB.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:08:37 PM
 */
public class VolumeInPotPreBoilBIAB  {

    /**
     * How much of the grains' weight is translated to volume.
     */
    private static final Factor GRAIN_VOLUME = new Factor(1);

    public Liters calc(Kilograms grainWeight, Liters preMashVolume) {
        return new Liters(grainWeight.value() * GRAIN_VOLUME.value() + preMashVolume.value());
    }

}

package blh.core.formulas.volumes.totalpot;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

/**
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:08:37 PM
 */
public class VolumeInPotBIAB implements Formula<Liters> {
    private static final double GRAIN_VOLUME = 1;

    @Override
    public Liters calc(FullContext context) {
        Kilograms grainWeight = context.getIngredientsList().getTotalGrainWeight();
        Liters preMashVolume = context.volumePre(context.MASH);
        
        return calc(grainWeight, preMashVolume);        
    }

    public Liters calc(Kilograms grainWeight, Liters preMashVolume) {
        return new Liters(grainWeight.value() * GRAIN_VOLUME + preMashVolume.value());
    }

}

package blh.core.formulas.volumes;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
* Created by Erik Larkö at 7/3/13 10:53 PM
*/
interface VolumeStepFormula {

    public Liters postStep(Liters preStep, FullContext context);

}

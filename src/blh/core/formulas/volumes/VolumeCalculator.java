package blh.core.formulas.volumes;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik Larkö at 7/3/13 10:45 PM
 */
public class VolumeCalculator {

    public static final VolumeStepFormula PRE_BOIL = new PreBoil();
    public static final VolumeStepFormula POST_BOIL = new PreBoil();
    public static final VolumeStepFormula POST_BOIL_BIAB = new PreBoil();
    public static final VolumeStepFormula POST_COOLING = new PreBoil();
    public static final VolumeStepFormula FINAL_VOLUME = new PreBoil();

    private Map<VolumeStepFormula, VolumeStepFormula> chain;

    public VolumeCalculator() {
        chain = new HashMap<>();
        chain.put(null, PRE_BOIL);
        chain.put(PRE_BOIL, POST_BOIL);
        chain.put(POST_BOIL, POST_COOLING);
        chain.put(POST_COOLING, FINAL_VOLUME);
        chain.put(FINAL_VOLUME, null);
    }

    public Liters volumeAt(VolumeStepFormula inclusive, FullContext context) {
        Liters l = calculateUpTo(inclusive, context);
        l = inclusive.postStep(l, context);
        return l;
    }

    private Liters calculateUpTo(VolumeStepFormula exclusive, FullContext context) {
        VolumeStepFormula f = chain.get(null);
        Liters l = null;
        while (f != exclusive && f != null) {
            l = f.postStep(l, context);
            f = chain.get(f);
        }

        return l;
    }

}

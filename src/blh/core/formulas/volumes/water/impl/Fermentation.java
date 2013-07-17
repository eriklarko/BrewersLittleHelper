package blh.core.formulas.volumes.water.impl;

import blh.core.formulas.volumes.water.impl.generic.AdditiveStep;
import blh.core.uncategorized.FullContext;

/**
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:45:03 PM
 */
public class Fermentation extends AdditiveStep {

    public Fermentation() {
        super(Volume.DECREASES_IN_STEP);
    }

    @Override
    protected double term(FullContext context) {
        return context.getEquipment().getTrubLoss().value();
    }
}

package org.blh.core.formulas.volumes.water.impl;

import org.blh.core.formulas.volumes.water.BrewStep;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;

/**
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:37:44 PM
 */
public class CoolingStep extends BrewStep {

    @Override
    protected Liters calculateVolumeAfterStep(Liters volumeBeforeStep, FullContext context) {
        double percentLeftAfterCooling = 1 - context.getEquipment().getCoolingLoss().asFactor().inexactValue();
        return new Liters(volumeBeforeStep.inexactValue() * percentLeftAfterCooling);
    }

    @Override
    protected Liters calculateVolumeBeforeStep(Liters volumeAfterStep, FullContext context) {
        double percentLostCooling = 1 + context.getEquipment().getCoolingLoss().asFactor().inexactValue();
        return new Liters(volumeAfterStep.inexactValue() * percentLostCooling);
    }
    
}

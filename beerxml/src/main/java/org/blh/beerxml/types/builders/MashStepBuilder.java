/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.MashStep;
import org.blh.beerxml.types.MashStep.MASH_STEP_TYPE;
import org.blh.core.units.temperature.Celsius;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public interface MashStepBuilder extends Builder<MashStep> {

    MashStepBuilder setEndTemp(Celsius endTemp);

    MashStepBuilder setInfusionAmount(Liters infusionAmount);

    MashStepBuilder setName(String name);

    MashStepBuilder setRampTime(Minutes rampTime);

    MashStepBuilder setStepTemp(Celsius stepTemp);

    MashStepBuilder setStepTime(Minutes stepTime);

    MashStepBuilder setType(MASH_STEP_TYPE type);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.MashStep;
import blh.core.beerxml.types.MashStep.MASH_STEP_TYPE;
import blh.core.units.temperature.Celcius;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public interface MashStepBuilder extends Builder<MashStep> {

    MashStepBuilder setEndTemp(Celcius endTemp);

    MashStepBuilder setInfusionAmount(Liters infusionAmount);

    MashStepBuilder setName(String name);

    MashStepBuilder setRampTime(Minutes rampTime);

    MashStepBuilder setStepTemp(Celcius stepTemp);

    MashStepBuilder setStepTime(Minutes stepTime);

    MashStepBuilder setType(MASH_STEP_TYPE type);
}

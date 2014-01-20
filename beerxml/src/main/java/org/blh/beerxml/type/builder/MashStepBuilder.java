package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.MashStep.MASH_STEP_TYPE;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;

/**
 * Builder for the MashStep type.
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

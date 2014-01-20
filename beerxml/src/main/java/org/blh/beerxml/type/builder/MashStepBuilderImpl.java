package org.blh.beerxml.type.builder;

import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.MashStep.MASH_STEP_TYPE;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;

/**
 * Builder for the MashStep type.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class MashStepBuilderImpl implements MashStepBuilder {

    private String name;
    private MASH_STEP_TYPE type;
    private Liters infuseAmount;
    private Celsius stepTemp;
    private Minutes stepTime;
    private Minutes rampTime;
    private Celsius endTemp;

    public MashStepBuilderImpl() {
    }

    @Override
    public MashStepBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public MashStepBuilderImpl setType(MASH_STEP_TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public MashStepBuilderImpl setInfusionAmount(Liters infusionAmount) {
        this.infuseAmount = infusionAmount;
        return this;
    }

    @Override
    public MashStepBuilderImpl setStepTemp(Celsius stepTemp) {
        this.stepTemp = stepTemp;
        return this;
    }

    @Override
    public MashStepBuilderImpl setStepTime(Minutes stepTime) {
        this.stepTime = stepTime;
        return this;
    }

    @Override
    public MashStepBuilderImpl setRampTime(Minutes rampTime) {
        this.rampTime = rampTime;
        return this;
    }

    @Override
    public MashStepBuilderImpl setEndTemp(Celsius endTemp) {
        this.endTemp = endTemp;
        return this;
    }

    // This is as complex as it needs to be
    @Override
    public Builder<MashStep> set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case MashStep.NAME:
                name = value;
                break;
            case MashStep.TYPE:
                type = MASH_STEP_TYPE.valueOf(value.toUpperCase());
                break;
            case MashStep.INFUSE_AMOUNT:
                infuseAmount = new Liters(Double.parseDouble(value));
                break;
            case MashStep.STEP_TEMP:
                stepTemp = new Celsius(Double.parseDouble(value));
                break;
            case MashStep.STEP_TIME:
                stepTime = new Minutes(Double.parseDouble(value));
                break;
            case MashStep.RAMP_TIME:
                rampTime = new Minutes(Double.parseDouble(value));
                break;
            case MashStep.END_TEMP:
                endTemp = new Celsius(Double.parseDouble(value));
                break;
            default:
                throw new UnknownTagException("Unknown mash step value: " + tagName);
        }
        return this;
    }

    @Override
    public MashStep create() {
        return new MashStep(name, type, infuseAmount, stepTemp, stepTime, rampTime, endTemp);
    }
}

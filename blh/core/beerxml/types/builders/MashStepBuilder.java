package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.MashStep;
import blh.core.beerxml.types.MashStep.TYPE;
import blh.core.units.temperature.Celcius;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

public class MashStepBuilder implements Builder<MashStep> {

    private String name;
    private TYPE type;
    private Liters infuseAmount;
    private Celcius stepTemp;
    private Minutes stepTime;
    private Minutes rampTime;
    private Celcius endTemp;

    public MashStepBuilder() {
    }

    public MashStepBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MashStepBuilder setType(TYPE type) {
        this.type = type;
        return this;
    }

    public MashStepBuilder setInfusionAmount(Liters infusionAmount) {
        this.infuseAmount = infusionAmount;
        return this;
    }

    public MashStepBuilder setStepTemp(Celcius stepTemp) {
        this.stepTemp = stepTemp;
        return this;
    }

    public MashStepBuilder setStepTime(Minutes stepTime) {
        this.stepTime = stepTime;
        return this;
    }

    public MashStepBuilder setRampTime(Minutes rampTime) {
        this.rampTime = rampTime;
        return this;
    }

    public MashStepBuilder setEndTemp(Celcius endTemp) {
        this.endTemp = endTemp;
        return this;
    }

    @Override
    public Builder<MashStep> set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "TYPE":
                type = TYPE.valueOf(value.toUpperCase());
                break;
            case "INFUSE_AMOUNT":
                infuseAmount = new Liters(Double.parseDouble(value));
                break;
            case "STEP_TEMP":
                stepTemp = new Celcius(Double.parseDouble(value));
                break;
            case "STEP_TIME":
                stepTime = new Minutes(Integer.parseInt(value));
                break;
            case "RAMP_TIME":
                rampTime = new Minutes(Integer.parseInt(value));
                break;
            case "END_TEMP":
                endTemp = new Celcius(Double.parseDouble(value));
                break;
            default:
                System.out.println("Unknown mash step value: " + tagName);
                break;
        }
        return this;
    }

    @Override
    public MashStep create() {
        return new MashStep(name, type, infuseAmount, stepTemp, stepTime, rampTime, endTemp);
    }
}

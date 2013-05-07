package blh.core.beerxml.types;

import blh.core.units.temperature.Celcius;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class MashStep {

    public final String name;
    public final TYPE type;
    /**
     * Not valid for type decoction.
     */
    public final Liters infuseAmount;
    public final Celcius stepTemp;
    public final Minutes stepTime;
    public final Minutes rampTime;
    public final Celcius endTemp;

    public static enum TYPE {

        INFUSION, TEMPERATURE, DECOCTION
    }

    public MashStep(String name, TYPE type, Liters infuseAmount, Celcius stepTemp, Minutes stepTime, Minutes rampTime, Celcius endTemp) {
        this.name = name;
        this.type = type;
        this.infuseAmount = infuseAmount;
        this.stepTemp = stepTemp;
        this.stepTime = stepTime;
        this.rampTime = rampTime;
        this.endTemp = endTemp;
    }
}

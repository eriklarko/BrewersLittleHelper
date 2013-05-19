package blh.core.beerxml.types;

import blh.core.units.temperature.Celcius;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class MashStep implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String INFUSE_AMOUNT = "INFUSE_AMOUNT";
    public static final String STEP_TEMP = "STEP_TEMP";
    public static final String STEP_TIME = "STEP_TIME";
    public static final String RAMP_TIME = "RAMP_TIME";
    public static final String END_TEMP = "END_TEMP";
    
    public final String name;
    public final MASH_STEP_TYPE type;
    /**
     * Not valid for type decoction.
     */
    public final Liters infuseAmount;
    public final Celcius stepTemp;
    public final Minutes stepTime;
    public final Minutes rampTime;
    public final Celcius endTemp;

    public static enum MASH_STEP_TYPE {

        INFUSION, TEMPERATURE, DECOCTION
    }

    public MashStep(String name, MASH_STEP_TYPE type, Liters infuseAmount, Celcius stepTemp, Minutes stepTime, Minutes rampTime, Celcius endTemp) {
        this.name = name;
        this.type = type;
        this.infuseAmount = infuseAmount;
        this.stepTemp = stepTemp;
        this.stepTime = stepTime;
        this.rampTime = rampTime;
        this.endTemp = endTemp;
    }
}

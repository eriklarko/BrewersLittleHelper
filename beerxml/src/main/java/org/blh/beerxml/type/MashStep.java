package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;

/**
 * Implementation of the BeerXML MashStep record.
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

    private final String name;
    private final MASH_STEP_TYPE type;
    /**
     * Not valid for type decoction.
     */
    private final Liters infuseAmount;
    private final Celsius stepTemp;
    private final Minutes stepTime;
    private final Minutes rampTime;
    private final Celsius endTemp;

    /**
     * Valid values for mash step
     */
    public static enum MASH_STEP_TYPE {

        INFUSION, TEMPERATURE, DECOCTION
    }

    public MashStep(String name, MASH_STEP_TYPE type, Liters infuseAmount,
            Celsius stepTemp, Minutes stepTime, Minutes rampTime, Celsius endTemp) {
        this.name = name;
        this.type = type;
        this.infuseAmount = infuseAmount;
        this.stepTemp = stepTemp;
        this.stepTime = stepTime;
        this.rampTime = rampTime;
        this.endTemp = endTemp;
    }

    public String getName() {
        return name;
    }

    public MASH_STEP_TYPE getType() {
        return type;
    }

    public Liters getInfuseAmount() {
        return infuseAmount;
    }

    public Celsius getStepTemp() {
        return stepTemp;
    }

    public Minutes getStepTime() {
        return stepTime;
    }

    public Minutes getRampTime() {
        return rampTime;
    }

    public Celsius getEndTemp() {
        return endTemp;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(TYPE, Utils.toStringOrNull(type));
        tagsAndValues.put(INFUSE_AMOUNT, Utils.toStringOrNull(infuseAmount));
        tagsAndValues.put(STEP_TEMP, Utils.toStringOrNull(stepTemp));
        tagsAndValues.put(STEP_TIME, Utils.toStringOrNull(stepTime));
        tagsAndValues.put(RAMP_TIME, Utils.toStringOrNull(rampTime));
        tagsAndValues.put(END_TEMP, Utils.toStringOrNull(endTemp));

        return tagsAndValues;
    }

    @Override
    public List<BeerXMLRecord> getSubRecords() {
        return null;
    }

    @Override
    public List<BeerXMLRecordSet> getSubRecordSets() {
        return null;
    }

    // This is as complex as it needs to be
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MashStep other = (MashStep) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.infuseAmount, other.infuseAmount)) {
            return false;
        }
        if (!Objects.equals(this.stepTemp, other.stepTemp)) {
            return false;
        }
        if (!Objects.equals(this.stepTime, other.stepTime)) {
            return false;
        }
        if (!Objects.equals(this.rampTime, other.rampTime)) {
            return false;
        }
        return Objects.equals(this.endTemp, other.endTemp);
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 23 * hash + Objects.hashCode(this.infuseAmount);
        hash = 23 * hash + Objects.hashCode(this.stepTemp);
        hash = 23 * hash + Objects.hashCode(this.stepTime);
        hash = 23 * hash + Objects.hashCode(this.rampTime);
        hash = 23 * hash + Objects.hashCode(this.endTemp);
        return hash;
    }
}

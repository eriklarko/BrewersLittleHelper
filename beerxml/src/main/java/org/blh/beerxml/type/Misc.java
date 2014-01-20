package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.time.Minutes;

/**
 * Implementation of the BeerXML Misc record.
 *
 * @author thinner
 */
public class Misc implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String USE = "USE";
    public static final String TIME = "TIME";
    public static final String AMOUNT = "AMOUNT";
    public static final String AMOUNT_IS_WEIGHT = "AMOUNT_IS_WEIGHT";
    public static final String USE_FOR = "USE_FOR";
    public static final String NOTES = "NOTES";
    private final String name;
    private final MISC_TYPE type;
    private final MISC_USE use;
    private final Minutes time;
    private final double amount;
    private final boolean amountIsWeight;
    private final String useFor;
    private final String notes;

    /**
     * Valid values for misc type
     */
    public static enum MISC_TYPE {

        SPICE, FINING, WATER, AGENT, HERB, FLAVOR, OTHER
    }

    /**
     * Valid values for misc use
     */
    public static enum MISC_USE {

        BOIL, MASH, PRIMARY, SECONDARY, BOTTLING
    }

    public Misc(String name, MISC_TYPE type, MISC_USE use, Minutes time,
            double amount, boolean amountIsWeight, String useFor, String notes) {
        this.name = name;
        this.type = type;
        this.use = use;
        this.time = time;
        this.amount = amount;
        this.amountIsWeight = amountIsWeight;
        this.useFor = useFor;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public MISC_TYPE getType() {
        return type;
    }

    public MISC_USE getUse() {
        return use;
    }

    public Minutes getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public boolean amountIsWeight() {
        return amountIsWeight;
    }

    public String getUseFor() {
        return useFor;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(TYPE, Utils.toStringOrNull(type));
        tagsAndValues.put(USE, Utils.toStringOrNull(use));
        tagsAndValues.put(TIME, Utils.toStringOrNull(time));
        tagsAndValues.put(AMOUNT, Utils.toStringOrNull(amount));
        tagsAndValues.put(AMOUNT_IS_WEIGHT, Utils.toStringOrNull(amountIsWeight));
        tagsAndValues.put(USE_FOR, Utils.toStringOrNull(useFor));
        tagsAndValues.put(NOTES, Utils.toStringOrNull(notes));

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
        final Misc other = (Misc) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.use != other.use) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (this.amountIsWeight != other.amountIsWeight) {
            return false;
        }
        if (!Objects.equals(this.useFor, other.useFor)) {
            return false;
        }
        return Objects.equals(this.notes, other.notes);
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 41 * hash + (this.use != null ? this.use.hashCode() : 0);
        hash = 41 * hash + Objects.hashCode(this.time);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 41 * hash + (this.amountIsWeight ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.useFor);
        hash = 41 * hash + Objects.hashCode(this.notes);
        return hash;
    }
}

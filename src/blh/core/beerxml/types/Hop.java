package blh.core.beerxml.types;

import blh.core.beerxml.Utils;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author thinner
 */
public class Hop implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String ALPHA = "ALPHA";
    public static final String AMOUNT = "AMOUNT";
    public static final String USE = "USE";
    public static final String TIME = "TIME";
    public static final String NOTES = "NOTES";
    public static final String TYPE = "TYPE";
    public static final String FORM = "FORM";
    public static final String BETA = "BETA";
    public static final String HOP_STABILITY_INDEX = "HSI";
    public static final String ORIGIN = "ORIGIN";
    public static final String SUBSTITUTES = "SUBSTITUTES";
    public static final String HUMULENE = "HUMULENE";
    public static final String CARYOPHYLLENE = "CARYOPHYLLENE";
    public static final String COHUMULONE = "COHUMULONE";
    public static final String MYRCENE = "MYRCENE";
    
    public final String name;
    public final Percentage alpha;
    public final Kilograms amount;
    public final HOP_USE use;
    public final Minutes time;
    public final String notes;
    public final HOP_TYPE type;
    public final HOP_FORM form;
    public final Percentage beta;
    public final Percentage hopStabilityIndex;
    public final String origin;
    public final String substitutes;
    public final Percentage humulene;
    public final Percentage caryophyllene;
    public final Percentage cohumulone;
    public final Percentage myrcene;

    public static enum HOP_USE {

        BOIL, DRY_HOP,
        MASH, FIRST_WORT,
        AROMA
    }

    public static enum HOP_TYPE {

        BITTERING, AROMA, BOTH
    }

    public static enum HOP_FORM {

        LEAF, PELLET, PLUG
    }

    public Hop(String name, Percentage alpha, Kilograms amount, HOP_USE use,
            Minutes time, String notes, HOP_TYPE type, HOP_FORM form, Percentage beta,
            Percentage hopStabilityIndex, String origin, String substitutes,
            Percentage humulene, Percentage caryophyllene,
            Percentage cohumulone, Percentage myrcene) {
        this.name = name;
        this.alpha = alpha;
        this.amount = amount;
        this.use = use;
        this.time = time;
        this.notes = notes;
        this.type = type;
        this.form = form;
        this.beta = beta;
        this.hopStabilityIndex = hopStabilityIndex;
        this.origin = origin;
        this.substitutes = substitutes;
        this.humulene = humulene;
        this.caryophyllene = caryophyllene;
        this.cohumulone = cohumulone;
        this.myrcene = myrcene;
    }
    
    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(ALPHA, Utils.toStringOrNull(alpha));
        tagsAndValues.put(AMOUNT, Utils.toStringOrNull(amount));
        tagsAndValues.put(USE, Utils.toStringOrNull(use));
        tagsAndValues.put(TIME, Utils.toStringOrNull(time));
        tagsAndValues.put(NOTES, Utils.toStringOrNull(notes));
        tagsAndValues.put(TYPE, Utils.toStringOrNull(type));
        tagsAndValues.put(FORM, Utils.toStringOrNull(form));
        tagsAndValues.put(BETA, Utils.toStringOrNull(beta));
        tagsAndValues.put(HOP_STABILITY_INDEX, Utils.toStringOrNull(hopStabilityIndex));
        tagsAndValues.put(ORIGIN, Utils.toStringOrNull(origin));
        tagsAndValues.put(SUBSTITUTES, Utils.toStringOrNull(substitutes));
        tagsAndValues.put(HUMULENE, Utils.toStringOrNull(humulene));
        tagsAndValues.put(CARYOPHYLLENE, Utils.toStringOrNull(caryophyllene));
        tagsAndValues.put(COHUMULONE, Utils.toStringOrNull(cohumulone));
        tagsAndValues.put(MYRCENE, Utils.toStringOrNull(myrcene));

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

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hop other = (Hop) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.alpha, other.alpha)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (this.use != other.use) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.form != other.form) {
            return false;
        }
        if (!Objects.equals(this.beta, other.beta)) {
            return false;
        }
        if (!Objects.equals(this.hopStabilityIndex, other.hopStabilityIndex)) {
            return false;
        }
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.substitutes, other.substitutes)) {
            return false;
        }
        if (!Objects.equals(this.humulene, other.humulene)) {
            return false;
        }
        if (!Objects.equals(this.caryophyllene, other.caryophyllene)) {
            return false;
        }
        if (!Objects.equals(this.cohumulone, other.cohumulone)) {
            return false;
        }
        if (!Objects.equals(this.myrcene, other.myrcene)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.alpha);
        hash = 59 * hash + Objects.hashCode(this.amount);
        hash = 59 * hash + (this.use != null ? this.use.hashCode() : 0);
        hash = 59 * hash + Objects.hashCode(this.time);
        hash = 59 * hash + Objects.hashCode(this.notes);
        hash = 59 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 59 * hash + (this.form != null ? this.form.hashCode() : 0);
        hash = 59 * hash + Objects.hashCode(this.beta);
        hash = 59 * hash + Objects.hashCode(this.hopStabilityIndex);
        hash = 59 * hash + Objects.hashCode(this.origin);
        hash = 59 * hash + Objects.hashCode(this.substitutes);
        hash = 59 * hash + Objects.hashCode(this.humulene);
        hash = 59 * hash + Objects.hashCode(this.caryophyllene);
        hash = 59 * hash + Objects.hashCode(this.cohumulone);
        hash = 59 * hash + Objects.hashCode(this.myrcene);
        return hash;
    }
}

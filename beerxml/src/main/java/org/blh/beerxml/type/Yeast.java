package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.temperature.Celsius;

/**
 * Implementation of the BeerXML Yeast record.
 *
 * @author thinner
 */
public class Yeast implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String FORM = "FORM";
    public static final String AMOUNT = "AMOUNT";
    public static final String AMOUNT_IS_WEIGHT = "AMOUNT_IS_WEIGHT";
    public static final String LABORATORY = "LABORATORY";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String MIN_TEMPERATURE = "MIN_TEMPERATURE";
    public static final String MAX_TEMPERATURE = "MAX_TEMPERATURE";
    public static final String FLOCCULATION = "FLOCCULATION";
    public static final String ATTENUATION = "ATTENUATION";
    public static final String NOTES = "NOTES";
    public static final String BEST_FOR = "BEST_FOR";
    public static final String TIMES_CULTURED = "TIMES_CULTURED";
    public static final String MAX_REUSE = "MAX_REUSE";
    public static final String ADD_TO_SECONDARY = "ADD_TO_SECONDARY";
    private final String name;
    private final YEAST_TYPE type;
    private final YEAST_FORM form;
    /**
     * Is kilograms if AMOUNT_IS_WEIGHT is true, liters otherwise
     */
    private final double amount;
    private final boolean amountIsWeight;
    private final String laboratory;
    private final String productId;
    private final Celsius minTemperature;
    private final Celsius maxTemperature;
    private final YEAST_FLOCCULATION flocculation;
    private final Percentage attenuation;
    private final String notes;
    private final String bestFor;
    private final int timesCultured;
    private final int maxReuse;
    private final boolean addToSecondary;

    /**
     * Valid values for YEAST TYPE
     */
    public static enum YEAST_TYPE {

        ALE, LAGER, WHEAT, WINE, CHAMPAGNE
    }

    /**
     * Valid values for YEAST FORM
     */
    public static enum YEAST_FORM {

        LIQUID, DRY, SLANT, CULTURE
    }

    /**
     * Valid values for YEAST FLOCCULATION
     */
    public static enum YEAST_FLOCCULATION {

        LOW, MEDIUM, HIGH, VERY_HIGH
    }

    public Yeast(String name, YEAST_TYPE type, YEAST_FORM form, double amount,
            boolean amountIsWeight, String laboratory, String productId,
            Celsius minTemperature, Celsius maxTemperature,
            YEAST_FLOCCULATION flocculation, Percentage attenuation, String notes,
            String bestFor, int timesCultured, int maxReuse, boolean addToSecondary) {
        this.name = name;
        this.type = type;
        this.form = form;
        this.amount = amount;
        this.amountIsWeight = amountIsWeight;
        this.laboratory = laboratory;
        this.productId = productId;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.flocculation = flocculation;
        this.attenuation = attenuation;
        this.notes = notes;
        this.bestFor = bestFor;
        this.timesCultured = timesCultured;
        this.maxReuse = maxReuse;
        this.addToSecondary = addToSecondary;
    }

    public String getName() {
        return name;
    }

    public YEAST_TYPE getType() {
        return type;
    }

    public YEAST_FORM getForm() {
        return form;
    }

    public double getAmount() {
        return amount;
    }

    public boolean amountIsWeight() {
        return amountIsWeight;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public String getProductId() {
        return productId;
    }

    public Celsius getMinTemperature() {
        return minTemperature;
    }

    public Celsius getMaxTemperature() {
        return maxTemperature;
    }

    public YEAST_FLOCCULATION getFlocculation() {
        return flocculation;
    }

    public Percentage getAttenuation() {
        return attenuation;
    }

    public String getNotes() {
        return notes;
    }

    public String getBestFor() {
        return bestFor;
    }

    public int getTimesCultured() {
        return timesCultured;
    }

    public int getMaxReuse() {
        return maxReuse;
    }

    public boolean addToSecondary() {
        return addToSecondary;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(TYPE, Utils.toStringOrNull(type));
        tagsAndValues.put(FORM, Utils.toStringOrNull(form));
        tagsAndValues.put(AMOUNT, Utils.toStringOrNull(amount));
        tagsAndValues.put(AMOUNT_IS_WEIGHT, Utils.toStringOrNull(amountIsWeight));
        tagsAndValues.put(LABORATORY, Utils.toStringOrNull(laboratory));
        tagsAndValues.put(PRODUCT_ID, Utils.toStringOrNull(productId));
        tagsAndValues.put(MIN_TEMPERATURE, Utils.toStringOrNull(minTemperature));
        tagsAndValues.put(MAX_TEMPERATURE, Utils.toStringOrNull(maxTemperature));
        tagsAndValues.put(FLOCCULATION, Utils.toStringOrNull(flocculation));
        tagsAndValues.put(ATTENUATION, Utils.toStringOrNull(attenuation));
        tagsAndValues.put(NOTES, Utils.toStringOrNull(notes));
        tagsAndValues.put(BEST_FOR, Utils.toStringOrNull(bestFor));
        tagsAndValues.put(TIMES_CULTURED, Utils.toStringOrNull(timesCultured));
        tagsAndValues.put(MAX_REUSE, Utils.toStringOrNull(maxReuse));
        tagsAndValues.put(ADD_TO_SECONDARY, Utils.toStringOrNull(addToSecondary));

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
        final Yeast other = (Yeast) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.form != other.form) {
            return false;
        }
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (this.amountIsWeight != other.amountIsWeight) {
            return false;
        }
        if (!Objects.equals(this.laboratory, other.laboratory)) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.minTemperature, other.minTemperature)) {
            return false;
        }
        if (!Objects.equals(this.maxTemperature, other.maxTemperature)) {
            return false;
        }
        if (this.flocculation != other.flocculation) {
            return false;
        }
        if (!Objects.equals(this.attenuation, other.attenuation)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.bestFor, other.bestFor)) {
            return false;
        }
        if (this.timesCultured != other.timesCultured) {
            return false;
        }
        if (this.maxReuse != other.maxReuse) {
            return false;
        }
        return this.addToSecondary == other.addToSecondary;
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 67 * hash + (this.form != null ? this.form.hashCode() : 0);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 67 * hash + (this.amountIsWeight ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.laboratory);
        hash = 67 * hash + Objects.hashCode(this.productId);
        hash = 67 * hash + Objects.hashCode(this.minTemperature);
        hash = 67 * hash + Objects.hashCode(this.maxTemperature);
        hash = 67 * hash + (this.flocculation != null ? this.flocculation.hashCode() : 0);
        hash = 67 * hash + Objects.hashCode(this.attenuation);
        hash = 67 * hash + Objects.hashCode(this.notes);
        hash = 67 * hash + Objects.hashCode(this.bestFor);
        hash = 67 * hash + this.timesCultured;
        hash = 67 * hash + this.maxReuse;
        hash = 67 * hash + (this.addToSecondary ? 1 : 0);
        return hash;
    }
}

package org.blh.beerxml.types;

import org.blh.beerxml.Utils;
import org.blh.core.units.PH;
import org.blh.core.units.PPM;
import org.blh.core.units.volume.Liters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author thinner
 */
public class Water implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String AMOUNT = "AMOUNT";
    public static final String CALCIUM = "CALCIUM";
    public static final String BICARBONATE = "BICARBONATE";
    public static final String SULFATE = "SULFATE";
    public static final String CHLORIDE = "CHLORIDE";
    public static final String SODIUM = "SODIUM";
    public static final String MAGNESIUM = "MAGNESIUM";
    public static final String PH = "PH";
    public static final String NOTES = "NOTES";
    public final String name;
    public final Liters amount;
    public final PPM calcium;
    public final PPM bicarbonate;
    public final PPM sulfate;
    public final PPM chloride;
    public final PPM sodium;
    public final PPM magnesium;
    public final PH ph;
    public final String notes;

    public Water(String name, Liters amount, PPM calcium, PPM bicarbonate, PPM sulfate, PPM chloride, PPM sodium, PPM magnesium, PH ph, String notes) {
        this.name = name;
        this.amount = amount;
        this.calcium = calcium;
        this.bicarbonate = bicarbonate;
        this.sulfate = sulfate;
        this.chloride = chloride;
        this.sodium = sodium;
        this.magnesium = magnesium;
        this.ph = ph;
        this.notes = notes;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(AMOUNT, Utils.toStringOrNull(amount));
        tagsAndValues.put(CALCIUM, Utils.toStringOrNull(calcium));
        tagsAndValues.put(BICARBONATE, Utils.toStringOrNull(bicarbonate));
        tagsAndValues.put(SULFATE, Utils.toStringOrNull(sulfate));
        tagsAndValues.put(CHLORIDE, Utils.toStringOrNull(chloride));
        tagsAndValues.put(SODIUM, Utils.toStringOrNull(sodium));
        tagsAndValues.put(MAGNESIUM, Utils.toStringOrNull(magnesium));
        tagsAndValues.put(PH, Utils.toStringOrNull(ph));
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

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Water other = (Water) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.calcium, other.calcium)) {
            return false;
        }
        if (!Objects.equals(this.bicarbonate, other.bicarbonate)) {
            return false;
        }
        if (!Objects.equals(this.sulfate, other.sulfate)) {
            return false;
        }
        if (!Objects.equals(this.chloride, other.chloride)) {
            return false;
        }
        if (!Objects.equals(this.sodium, other.sodium)) {
            return false;
        }
        if (!Objects.equals(this.magnesium, other.magnesium)) {
            return false;
        }
        if (!Objects.equals(this.ph, other.ph)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.amount);
        hash = 97 * hash + Objects.hashCode(this.calcium);
        hash = 97 * hash + Objects.hashCode(this.bicarbonate);
        hash = 97 * hash + Objects.hashCode(this.sulfate);
        hash = 97 * hash + Objects.hashCode(this.chloride);
        hash = 97 * hash + Objects.hashCode(this.sodium);
        hash = 97 * hash + Objects.hashCode(this.magnesium);
        hash = 97 * hash + Objects.hashCode(this.ph);
        hash = 97 * hash + Objects.hashCode(this.notes);
        return hash;
    }
}

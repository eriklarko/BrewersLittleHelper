package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.PH;
import org.blh.core.unit.PPM;
import org.blh.core.unit.volume.Liters;

/**
 * Implementation of the BeerXML Water record.
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
    private final String name;
    private final Liters amount;
    private final PPM calcium;
    private final PPM bicarbonate;
    private final PPM sulfate;
    private final PPM chloride;
    private final PPM sodium;
    private final PPM magnesium;
    private final PH ph;
    private final String notes;

    public Water(String name, Liters amount, PPM calcium, PPM bicarbonate,
            PPM sulfate, PPM chloride, PPM sodium, PPM magnesium, PH ph, String notes) {
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

    public String getName() {
        return name;
    }

    public Liters getAmount() {
        return amount;
    }

    public PPM getCalcium() {
        return calcium;
    }

    public PPM getBicarbonate() {
        return bicarbonate;
    }

    public PPM getSulfate() {
        return sulfate;
    }

    public PPM getChloride() {
        return chloride;
    }

    public PPM getSodium() {
        return sodium;
    }

    public PPM getMagnesium() {
        return magnesium;
    }

    public PH getPH() {
        return ph;
    }

    public String getNotes() {
        return notes;
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

    // This is as complex as it needs to be
    @Override
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
        return Objects.equals(this.notes, other.notes);
    }

    // This hash is ok
    @Override
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

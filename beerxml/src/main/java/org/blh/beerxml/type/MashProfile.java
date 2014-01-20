package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.PH;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.weight.Kilograms;

/**
 * Implementation of the BeerXML MashProfile record.
 *
 * @author thinner
 */
public class MashProfile implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String GRAIN_TEMPERATURE = "GRAIN_TEMP";
    public static final String NOTES = "NOTES";
    public static final String TUN_TEMPERATURE = "TUN_TEMP";
    public static final String SPARGE_TEMPERATURE = "SPARGE_TEMP";
    public static final String SPARGE_PH = "PH";
    public static final String TUN_WEIGHT = "TUN_WEIGHT";
    public static final String TUN_SPECIFIC_HEAT = "TUN_SPECIFIC_HEAT";
    public static final String ADJUST_FOR_EQUIPMENT_TEMPERATURE = "EQUIP_ADJUST";
    private final String name;
    private final Celsius grainTemperature;
    private final List<MashStep> mashSteps;
    private final String notes;
    private final Celsius tunTemperature;
    private final Celsius spargeTemperature;
    private final PH spargePH;
    private final Kilograms tunWeight;
    private final double tunSpecificHeat;
    private final boolean adjustForEquipmentTemperature;

    public MashProfile(String name, Celsius grainTemperature, List<MashStep> mashSteps,
            String notes, Celsius tunTemperature, Celsius spargeTemperature, PH spargePH,
            Kilograms tunWeight, double tunSpecificHeat, boolean adjustForEquipmentTemperature) {
        this.name = name;
        this.grainTemperature = grainTemperature;
        this.mashSteps = mashSteps;
        this.notes = notes;
        this.tunTemperature = tunTemperature;
        this.spargeTemperature = spargeTemperature;
        this.spargePH = spargePH;
        this.tunWeight = tunWeight;
        this.tunSpecificHeat = tunSpecificHeat;
        this.adjustForEquipmentTemperature = adjustForEquipmentTemperature;
    }

    public String getName() {
        return name;
    }

    public Celsius getGrainTemperature() {
        return grainTemperature;
    }

    public List<MashStep> getMashSteps() {
        return mashSteps;
    }

    public String getNotes() {
        return notes;
    }

    public Celsius getTunTemperature() {
        return tunTemperature;
    }

    public Celsius getSpargeTemperature() {
        return spargeTemperature;
    }

    public PH getSpargePH() {
        return spargePH;
    }

    public Kilograms getTunWeight() {
        return tunWeight;
    }

    public double getTunSpecificHeat() {
        return tunSpecificHeat;
    }

    public boolean adjustForEquipmentTemperature() {
        return adjustForEquipmentTemperature;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(GRAIN_TEMPERATURE, Utils.toStringOrNull(grainTemperature));
        tagsAndValues.put(NOTES, Utils.toStringOrNull(notes));
        tagsAndValues.put(TUN_TEMPERATURE, Utils.toStringOrNull(tunTemperature));
        tagsAndValues.put(SPARGE_TEMPERATURE, Utils.toStringOrNull(spargeTemperature));
        tagsAndValues.put(SPARGE_PH, Utils.toStringOrNull(spargePH));
        tagsAndValues.put(TUN_WEIGHT, Utils.toStringOrNull(tunWeight));
        tagsAndValues.put(TUN_SPECIFIC_HEAT, Utils.toStringOrNull(tunSpecificHeat));
        tagsAndValues.put(ADJUST_FOR_EQUIPMENT_TEMPERATURE, Utils.toStringOrNull(adjustForEquipmentTemperature));

        return tagsAndValues;
    }

    @Override
    public List<BeerXMLRecord> getSubRecords() {
        return null;
    }

    @Override
    public List<BeerXMLRecordSet> getSubRecordSets() {
        List<BeerXMLRecordSet> recordSets = new LinkedList<>();
        BeerXMLRecordSet<MashStep> mashStepsSubRecord = new BeerXMLRecordSet<>(MashStep.class, this.mashSteps);
        recordSets.add(mashStepsSubRecord);

        return recordSets;
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
        final MashProfile other = (MashProfile) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.grainTemperature, other.grainTemperature)) {
            return false;
        }
        if (!Objects.equals(this.mashSteps, other.mashSteps)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.tunTemperature, other.tunTemperature)) {
            return false;
        }
        if (!Objects.equals(this.spargeTemperature, other.spargeTemperature)) {
            return false;
        }
        if (!Objects.equals(this.spargePH, other.spargePH)) {
            return false;
        }
        if (!Objects.equals(this.tunWeight, other.tunWeight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tunSpecificHeat) != Double.doubleToLongBits(other.tunSpecificHeat)) {
            return false;
        }
        return this.adjustForEquipmentTemperature == other.adjustForEquipmentTemperature;
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.grainTemperature);
        hash = 37 * hash + Objects.hashCode(this.mashSteps);
        hash = 37 * hash + Objects.hashCode(this.notes);
        hash = 37 * hash + Objects.hashCode(this.tunTemperature);
        hash = 37 * hash + Objects.hashCode(this.spargeTemperature);
        hash = 37 * hash + Objects.hashCode(this.spargePH);
        hash = 37 * hash + Objects.hashCode(this.tunWeight);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.tunSpecificHeat) ^ (Double.doubleToLongBits(this.tunSpecificHeat) >>> 32));
        hash = 37 * hash + (this.adjustForEquipmentTemperature ? 1 : 0);
        return hash;
    }
}

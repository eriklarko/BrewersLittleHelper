package blh.core.beerxml.types;

import blh.core.beerxml.Utils;
import blh.core.units.PH;
import blh.core.units.temperature.Celcius;
import blh.core.units.weight.Kilograms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
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
    public final String name;
    public final Celcius grainTemperature;
    public final List<MashStep> mashSteps;
    public final String notes;
    public final Celcius tunTemperature;
    public final Celcius spargeTemperature;
    public final PH spargePH;
    public final Kilograms tunWeight;
    public final double tunSpecificHeat;
    public final boolean adjustForEquipmentTemperature;

    public MashProfile(String name, Celcius grainTemperature, List<MashStep> mashSteps, String notes, Celcius tunTemperature, Celcius spargeTemperature, PH spargePH, Kilograms tunWeight, double tunSpecificHeat, boolean adjustForEquipmentTemperature) {
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
        BeerXMLRecordSet<MashStep> mashSteps = new BeerXMLRecordSet<>(MashStep.class, this.mashSteps);
        recordSets.add(mashSteps);

        return recordSets;
    }

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
        if (this.adjustForEquipmentTemperature != other.adjustForEquipmentTemperature) {
            return false;
        }
        return true;
    }

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

package blh.core.beerxml.types;

import blh.core.units.PH;
import blh.core.units.temperature.Celcius;
import blh.core.units.weight.Kilograms;
import java.util.List;

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
}

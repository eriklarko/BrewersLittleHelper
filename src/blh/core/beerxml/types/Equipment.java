package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class Equipment implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String BOIL_SIZE = "BOIL_SIZE";
    public static final String BATCH_SIZE = "BATCH_SIZE";
    public static final String TUN_VOLUME = "TUN_VOLUME";
    public static final String TUN_WEIGHT = "TUN_WEIGHT";
    public static final String TUN_SPECIFIC_HEAT = "TUN_SPECIFIC_HEAT";
    public static final String TOP_UP_WATER = "TOP_UP_WATER";
    public static final String TRUB_CHILLER_LOSS = "TRUB_CHILLER_LOSS";
    public static final String EVAP_RATE = "EVAP_RATE";
    public static final String BOIL_TIME = "BOIL_TIME";
    public static final String CALCULATE_BOIL_VOLUME = "CALC_BOIL_VOLUME";
    public static final String LAUTER_DEAD_SPACE = "LAUTER_DEADSPACE";
    public static final String TOP_UP_KETTLE = "TOP_UP_KETTLE";
    public static final String HOP_UTILIZATION = "HOP_UTILIZATION";
    public static final String NOTES = "NOTES";
    
    public final String name;
    public final Liters boilSize;
    public final Liters batchSize;
    public final Liters tunVolume;
    public final Kilograms tunWeight;
    public final double tunSpecificHeat;
    public final Liters topUpWater;
    public final Liters trubChillerLoss;
    public final Percentage evapRate;
    public final Minutes boilTime;
    public final boolean calculateBoilVolume;
    public final Liters lauterDeadSpace;
    public final Liters topUpKettle;
    public final Percentage hopUtilization;
    public final String notes;

    public Equipment(String name, Liters boilSize, Liters batchSize, Liters tunVolume, Kilograms tunWeight, double tunSpecificHeat, Liters topUpWater, Liters trubChillerLoss, Percentage evapRate, Minutes boilTime, boolean calculateBoilVolume, Liters lauterDeadSpace, Liters topUpKettle, Percentage hopUtilization, String notes) {
        this.name = name;
        this.boilSize = boilSize;
        this.batchSize = batchSize;
        this.tunVolume = tunVolume;
        this.tunWeight = tunWeight;
        this.tunSpecificHeat = tunSpecificHeat;
        this.topUpWater = topUpWater;
        this.trubChillerLoss = trubChillerLoss;
        this.evapRate = evapRate;
        this.boilTime = boilTime;
        this.calculateBoilVolume = calculateBoilVolume;
        this.lauterDeadSpace = lauterDeadSpace;
        this.topUpKettle = topUpKettle;
        this.hopUtilization = hopUtilization;
        this.notes = notes;
    }
}

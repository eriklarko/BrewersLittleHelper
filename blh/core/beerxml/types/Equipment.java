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

package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Kilograms;

/**
 * Implementation of the BeerXML Equipment record.
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
    private final String name;
    private final Liters boilSize;
    private final Liters batchSize;
    private final Liters tunVolume;
    private final Kilograms tunWeight;
    private final double tunSpecificHeat;
    private final Liters topUpWater;
    private final Liters trubChillerLoss;
    private final Percentage evapRate;
    private final Minutes boilTime;
    private final boolean calculateBoilVolume;
    private final Liters lauterDeadSpace;
    private final Liters topUpKettle;
    private final Percentage hopUtilization;
    private final String notes;

    public Equipment(String name, Liters boilSize, Liters batchSize,
            Liters tunVolume, Kilograms tunWeight, double tunSpecificHeat,
            Liters topUpWater, Liters trubChillerLoss, Percentage evapRate,
            Minutes boilTime, boolean calculateBoilVolume, Liters lauterDeadSpace,
            Liters topUpKettle, Percentage hopUtilization, String notes) {
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

    public String getName() {
        return name;
    }

    public Liters getBoilSize() {
        return boilSize;
    }

    public Liters getBatchSize() {
        return batchSize;
    }

    public Liters getTunVolume() {
        return tunVolume;
    }

    public Kilograms getTunWeight() {
        return tunWeight;
    }

    public double getTunSpecificHeat() {
        return tunSpecificHeat;
    }

    public Liters getTopUpWater() {
        return topUpWater;
    }

    public Liters getTrubChillerLoss() {
        return trubChillerLoss;
    }

    public Percentage getEvapRate() {
        return evapRate;
    }

    public Minutes getBoilTime() {
        return boilTime;
    }

    public boolean isCalculateBoilVolume() {
        return calculateBoilVolume;
    }

    public Liters getLauterDeadSpace() {
        return lauterDeadSpace;
    }

    public Liters getTopUpKettle() {
        return topUpKettle;
    }

    public Percentage getHopUtilization() {
        return hopUtilization;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(BOIL_SIZE, Utils.toStringOrNull(boilSize));
        tagsAndValues.put(BATCH_SIZE, Utils.toStringOrNull(batchSize));
        tagsAndValues.put(TUN_VOLUME, Utils.toStringOrNull(tunVolume));
        tagsAndValues.put(TUN_WEIGHT, Utils.toStringOrNull(tunWeight));
        tagsAndValues.put(TUN_SPECIFIC_HEAT, Utils.toStringOrNull(tunSpecificHeat));
        tagsAndValues.put(TOP_UP_WATER, Utils.toStringOrNull(topUpWater));
        tagsAndValues.put(TRUB_CHILLER_LOSS, Utils.toStringOrNull(trubChillerLoss));
        tagsAndValues.put(EVAP_RATE, Utils.toStringOrNull(evapRate));
        tagsAndValues.put(BOIL_TIME, Utils.toStringOrNull(boilTime));
        tagsAndValues.put(CALCULATE_BOIL_VOLUME, Utils.toStringOrNull(calculateBoilVolume));
        tagsAndValues.put(LAUTER_DEAD_SPACE, Utils.toStringOrNull(lauterDeadSpace));
        tagsAndValues.put(TOP_UP_KETTLE, Utils.toStringOrNull(topUpKettle));
        tagsAndValues.put(HOP_UTILIZATION, Utils.toStringOrNull(hopUtilization));
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
        final Equipment other = (Equipment) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.boilSize, other.boilSize)) {
            return false;
        }
        if (!Objects.equals(this.batchSize, other.batchSize)) {
            return false;
        }
        if (!Objects.equals(this.tunVolume, other.tunVolume)) {
            return false;
        }
        if (!Objects.equals(this.tunWeight, other.tunWeight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tunSpecificHeat) != Double.doubleToLongBits(other.tunSpecificHeat)) {
            return false;
        }
        if (!Objects.equals(this.topUpWater, other.topUpWater)) {
            return false;
        }
        if (!Objects.equals(this.trubChillerLoss, other.trubChillerLoss)) {
            return false;
        }
        if (!Objects.equals(this.evapRate, other.evapRate)) {
            return false;
        }
        if (!Objects.equals(this.boilTime, other.boilTime)) {
            return false;
        }
        if (this.calculateBoilVolume != other.calculateBoilVolume) {
            return false;
        }
        if (!Objects.equals(this.lauterDeadSpace, other.lauterDeadSpace)) {
            return false;
        }
        if (!Objects.equals(this.topUpKettle, other.topUpKettle)) {
            return false;
        }
        if (!Objects.equals(this.hopUtilization, other.hopUtilization)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.boilSize);
        hash = 73 * hash + Objects.hashCode(this.batchSize);
        hash = 73 * hash + Objects.hashCode(this.tunVolume);
        hash = 73 * hash + Objects.hashCode(this.tunWeight);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.tunSpecificHeat) ^ (Double.doubleToLongBits(this.tunSpecificHeat) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.topUpWater);
        hash = 73 * hash + Objects.hashCode(this.trubChillerLoss);
        hash = 73 * hash + Objects.hashCode(this.evapRate);
        hash = 73 * hash + Objects.hashCode(this.boilTime);
        hash = 73 * hash + (this.calculateBoilVolume ? 1 : 0);
        hash = 73 * hash + Objects.hashCode(this.lauterDeadSpace);
        hash = 73 * hash + Objects.hashCode(this.topUpKettle);
        hash = 73 * hash + Objects.hashCode(this.hopUtilization);
        hash = 73 * hash + Objects.hashCode(this.notes);
        return hash;
    }
}

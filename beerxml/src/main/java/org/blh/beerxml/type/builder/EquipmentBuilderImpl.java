package org.blh.beerxml.type.builder;

import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.Equipment;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Kilograms;

/**
 * Builder for Equipment.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class EquipmentBuilderImpl implements EquipmentBuilder {

    private String name;
    private Liters boilSize;
    private Liters batchSize;
    private Liters tunVolume;
    private Kilograms tunWeight;
    private double tunSpecificHeat;
    private Liters topUpWater;
    private Liters trubChillerLoss;
    private Percentage evapRate;
    private Minutes boilTime;
    private boolean calculateBoilVolume;
    private Liters lauterDeadSpace;
    private Liters topUpKettle;
    private Percentage hopUtilization;
    private String notes;

    public EquipmentBuilderImpl() {
    }

    @Override
    public EquipmentBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setBoilSize(Liters boilSize) {
        this.boilSize = boilSize;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setBatchSize(Liters batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setTunVolume(Liters tunVolume) {
        this.tunVolume = tunVolume;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setTunWeight(Kilograms tunWeight) {
        this.tunWeight = tunWeight;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setTunSpecificHeat(double tunSpecificHeat) {
        this.tunSpecificHeat = tunSpecificHeat;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setTopUpWater(Liters topUpWater) {
        this.topUpWater = topUpWater;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setTrubChillerLoss(Liters trubChillerLoss) {
        this.trubChillerLoss = trubChillerLoss;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setEvapRate(Percentage evapRate) {
        this.evapRate = evapRate;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setBoilTime(Minutes boilTime) {
        this.boilTime = boilTime;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setCalculateBoilVolume(boolean calculateBoilVolume) {
        this.calculateBoilVolume = calculateBoilVolume;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setLauterDeadSpace(Liters lauterDeadSpace) {
        this.lauterDeadSpace = lauterDeadSpace;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setTopUpKettle(Liters topUpKettle) {
        this.topUpKettle = topUpKettle;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setHopUtilization(Percentage hopUtilization) {
        this.hopUtilization = hopUtilization;
        return this;
    }

    @Override
    public EquipmentBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    // This is as complex as it needs to be
    @Override
    public EquipmentBuilderImpl set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case Equipment.NAME:
                this.name = value;
                break;
            case Equipment.BOIL_SIZE:
                this.boilSize = new Liters(Double.parseDouble(value));
                break;
            case Equipment.BATCH_SIZE:
                this.batchSize = new Liters(Double.parseDouble(value));
                break;
            case Equipment.TUN_VOLUME:
                this.tunVolume = new Liters(Double.parseDouble(value));
                break;
            case Equipment.TUN_WEIGHT:
                this.tunWeight = new Kilograms(Double.parseDouble(value));
                break;
            case Equipment.TUN_SPECIFIC_HEAT:
                this.tunSpecificHeat = Double.parseDouble(value);
                break;
            case Equipment.TOP_UP_WATER:
                this.topUpWater = new Liters(Double.parseDouble(value));
                break;
            case Equipment.TRUB_CHILLER_LOSS:
                this.trubChillerLoss = new Liters(Double.parseDouble(value));
                break;
            case Equipment.EVAP_RATE:
                this.evapRate = new Percentage(Double.parseDouble(value));
                break;
            case Equipment.BOIL_TIME:
                this.boilTime = new Minutes(Double.parseDouble(value));
                break;
            case Equipment.CALCULATE_BOIL_VOLUME:
                this.calculateBoilVolume = Boolean.parseBoolean(value);
                break;
            case Equipment.LAUTER_DEAD_SPACE:
                this.lauterDeadSpace = new Liters(Double.parseDouble(value));
                break;
            case Equipment.TOP_UP_KETTLE:
                this.topUpKettle = new Liters(Double.parseDouble(value));
                break;
            case Equipment.HOP_UTILIZATION:
                this.hopUtilization = new Percentage(Double.parseDouble(value));
                break;
            case Equipment.NOTES:
                this.notes = value;
                break;
            default:
                throw new UnknownTagException("Unknown equipment tag: " + tagName);
        }
        return this;
    }

    @Override
    public Equipment create() {
        return new Equipment(name, boilSize, batchSize, tunVolume, tunWeight, tunSpecificHeat,
                topUpWater, trubChillerLoss, evapRate, boilTime, calculateBoilVolume, lauterDeadSpace,
                topUpKettle, hopUtilization, notes);
    }
}

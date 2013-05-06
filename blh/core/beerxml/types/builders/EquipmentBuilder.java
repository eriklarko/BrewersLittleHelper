package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Equipment;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

public class EquipmentBuilder implements Builder<Equipment>{

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
    private boolean calculatedBoilVolume;
    private Liters lauterDeadSpace;
    private Liters topUpKettle;
    private Percentage hopUtilization;
    private String notes;

    public EquipmentBuilder() {
    }

    public EquipmentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EquipmentBuilder setBoilSize(Liters boilSize) {
        this.boilSize = boilSize;
        return this;
    }

    public EquipmentBuilder setBatchSize(Liters batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public EquipmentBuilder setTunVolume(Liters tunVolume) {
        this.tunVolume = tunVolume;
        return this;
    }

    public EquipmentBuilder setTunWeight(Kilograms tunWeight) {
        this.tunWeight = tunWeight;
        return this;
    }

    public EquipmentBuilder setTunSpecificHeat(double tunSpecificHeat) {
        this.tunSpecificHeat = tunSpecificHeat;
        return this;
    }

    public EquipmentBuilder setTopUpWater(Liters topUpWater) {
        this.topUpWater = topUpWater;
        return this;
    }

    public EquipmentBuilder setTrubChillerLoss(Liters trubChillerLoss) {
        this.trubChillerLoss = trubChillerLoss;
        return this;
    }

    public EquipmentBuilder setEvapRate(Percentage evapRate) {
        this.evapRate = evapRate;
        return this;
    }

    public EquipmentBuilder setBoilTime(Minutes boilTime) {
        this.boilTime = boilTime;
        return this;
    }

    public EquipmentBuilder setCalculatedBoilVolume(boolean calculatedBoilVolume) {
        this.calculatedBoilVolume = calculatedBoilVolume;
        return this;
    }

    public EquipmentBuilder setLauterDeadSpace(Liters lauterDeadSpace) {
        this.lauterDeadSpace = lauterDeadSpace;
        return this;
    }

    public EquipmentBuilder setTopUpKettle(Liters topUpKettle) {
        this.topUpKettle = topUpKettle;
        return this;
    }

    public EquipmentBuilder setHopUtilization(Percentage hopUtilization) {
        this.hopUtilization = hopUtilization;
        return this;
    }

    public EquipmentBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public EquipmentBuilder set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                this.name = value;
                break;
            case "BOIL_SIZE":
                this.boilSize = new Liters(Double.parseDouble(value));
                break;
            case "BATCH_SIZE":
                this.batchSize = new Liters(Double.parseDouble(value));
                break;
            case "TUN_VOLUME":
                this.tunVolume = new Liters(Double.parseDouble(value));
                break;
            case "TUN_WEIGHT":
                this.tunWeight = new Kilograms(Double.parseDouble(value));
                break;
            case "TUN_SPECIFIC_HEAT":
                this.tunSpecificHeat = Double.parseDouble(value);
                break;
            case "TOP_UP_WATER":
                this.topUpWater = new Liters(Double.parseDouble(value));
                break;
            case "TRUB_CHILLER_LOSS":
                this.trubChillerLoss = new Liters(Double.parseDouble(value));
                break;
            case "EVAP_RATE":
                this.evapRate = new Percentage(Double.parseDouble(value));
                break;
            case "BOIL_TIME":
                this.boilTime = new Minutes(Integer.parseInt(value));
                break;
            case "CALC_BOIL_VOLUME":
                this.calculatedBoilVolume = Boolean.parseBoolean(value);
                break;
            case "LATUER_DEADSPACE":
                this.lauterDeadSpace = new Liters(Double.parseDouble(value));
                break;
            case "TOP_UP_KETTLE":
                this.topUpKettle = new Liters(Double.parseDouble(value));
                break;
            case "HOP_UTILIZATION":
                this.hopUtilization = new Percentage(Double.parseDouble(value));
                break;
            case "NOTES":
                this.notes = value;
                break;
            default:
                System.out.println("Unknown equipment value: " + value);
                break;
        }
        return this;
    }

    @Override
    public Equipment create() {
        return new Equipment(name, boilSize, batchSize, tunVolume, tunWeight, tunSpecificHeat, topUpWater, trubChillerLoss, evapRate, boilTime, calculatedBoilVolume, lauterDeadSpace, topUpKettle, hopUtilization, notes);
    }
}

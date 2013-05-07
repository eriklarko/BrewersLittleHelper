package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.MashStep;
import blh.core.units.PH;
import blh.core.units.temperature.Celcius;
import blh.core.units.weight.Kilograms;
import java.util.List;

public class MashProfileBuilder implements Builder<MashProfile> {

    private String name;
    private Celcius grainTemperature;
    private List<MashStep> mashSteps;
    private String notes;
    private Celcius tunTemperature;
    private Celcius spargeTemperature;
    private PH spargePH;
    private Kilograms tunWeight;
    private double tunSpecificHeat;
    private boolean adjustForEquipmentTemperature;

    public MashProfileBuilder() {
    }

    public MashProfileBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MashProfileBuilder setGrainTemperature(Celcius grainTemperature) {
        this.grainTemperature = grainTemperature;
        return this;
    }

    public MashProfileBuilder setMashSteps(List<MashStep> mashSteps) {
        this.mashSteps = mashSteps;
        return this;
    }

    public MashProfileBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public MashProfileBuilder setTunTemperature(Celcius tunTemperature) {
        this.tunTemperature = tunTemperature;
        return this;
    }

    public MashProfileBuilder setSpargeTemperature(Celcius spargeTemperature) {
        this.spargeTemperature = spargeTemperature;
        return this;
    }

    public MashProfileBuilder setSpargePH(PH spargePH) {
        this.spargePH = spargePH;
        return this;
    }

    public MashProfileBuilder setTunWeight(Kilograms tunWeight) {
        this.tunWeight = tunWeight;
        return this;
    }

    public MashProfileBuilder setTunSpecificHeat(double tunSpecificHeat) {
        this.tunSpecificHeat = tunSpecificHeat;
        return this;
    }

    public MashProfileBuilder setAdjustForEquipmentTemperature(boolean adjustForEquipmentTemperature) {
        this.adjustForEquipmentTemperature = adjustForEquipmentTemperature;
        return this;
    }

    @Override
    public Builder<MashProfile> set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "GRAIN_TEMP":
                grainTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "NOTES":
                notes = value;
                break;
            case "TUN_TEMP":
                tunTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "SPARGE_TEMP":
                spargeTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "PH":
                spargePH = new PH(Double.parseDouble(value));
                break;
            case "TUN_WEIGHT":
                tunWeight = new Kilograms(Double.parseDouble(value));
                break;
            case "TUN_SPECIFIC_HEAT":
                tunSpecificHeat = Double.parseDouble(value);
                break;
            case "EQUIP_ADJUST":
                adjustForEquipmentTemperature = Boolean.parseBoolean(value);
                break;
            default:
                System.out.println("Unknown mash profile value: " + tagName);
                break;
        }

        return this;
    }

    @Override
    public MashProfile create() {
        return new MashProfile(name, grainTemperature, mashSteps, notes, tunTemperature, spargeTemperature, spargePH, tunWeight, tunSpecificHeat, adjustForEquipmentTemperature);
    }
}

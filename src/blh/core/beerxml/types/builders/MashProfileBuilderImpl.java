package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.MashStep;
import blh.core.units.PH;
import blh.core.units.temperature.Celcius;
import blh.core.units.weight.Kilograms;
import java.util.List;

public class MashProfileBuilderImpl implements MashProfileBuilder {

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

    public MashProfileBuilderImpl() {
    }

    @Override
    public MashProfileBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setGrainTemperature(Celcius grainTemperature) {
        this.grainTemperature = grainTemperature;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setMashSteps(List<MashStep> mashSteps) {
        this.mashSteps = mashSteps;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setTunTemperature(Celcius tunTemperature) {
        this.tunTemperature = tunTemperature;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setSpargeTemperature(Celcius spargeTemperature) {
        this.spargeTemperature = spargeTemperature;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setSpargePH(PH spargePH) {
        this.spargePH = spargePH;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setTunWeight(Kilograms tunWeight) {
        this.tunWeight = tunWeight;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setTunSpecificHeat(double tunSpecificHeat) {
        this.tunSpecificHeat = tunSpecificHeat;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setAdjustForEquipmentTemperature(boolean adjustForEquipmentTemperature) {
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

package org.blh.beerxml.type.builder;

import java.util.List;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.MashStep;
import org.blh.core.unit.PH;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.weight.Kilograms;

/**
 * Builder for the MashProfile type.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class MashProfileBuilderImpl implements MashProfileBuilder {

    private String name;
    private Celsius grainTemperature;
    private List<MashStep> mashSteps;
    private String notes;
    private Celsius tunTemperature;
    private Celsius spargeTemperature;
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
    public MashProfileBuilderImpl setGrainTemperature(Celsius grainTemperature) {
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
    public MashProfileBuilderImpl setTunTemperature(Celsius tunTemperature) {
        this.tunTemperature = tunTemperature;
        return this;
    }

    @Override
    public MashProfileBuilderImpl setSpargeTemperature(Celsius spargeTemperature) {
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

    // This is as complex as it needs to be
    @Override
    public Builder<MashProfile> set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case MashProfile.NAME:
                name = value;
                break;
            case MashProfile.GRAIN_TEMPERATURE:
                grainTemperature = new Celsius(Double.parseDouble(value));
                break;
            case MashProfile.NOTES:
                notes = value;
                break;
            case MashProfile.TUN_TEMPERATURE:
                tunTemperature = new Celsius(Double.parseDouble(value));
                break;
            case MashProfile.SPARGE_TEMPERATURE:
                spargeTemperature = new Celsius(Double.parseDouble(value));
                break;
            case MashProfile.SPARGE_PH:
                spargePH = new PH(Double.parseDouble(value));
                break;
            case MashProfile.TUN_WEIGHT:
                tunWeight = new Kilograms(Double.parseDouble(value));
                break;
            case MashProfile.TUN_SPECIFIC_HEAT:
                tunSpecificHeat = Double.parseDouble(value);
                break;
            case MashProfile.ADJUST_FOR_EQUIPMENT_TEMPERATURE:
                adjustForEquipmentTemperature = Boolean.parseBoolean(value);
                break;
            default:
                throw new UnknownTagException("Unknown mash profile tag: " + tagName);
        }

        return this;
    }

    @Override
    public MashProfile create() {
        return new MashProfile(name, grainTemperature, mashSteps, notes, tunTemperature,
                spargeTemperature, spargePH, tunWeight, tunSpecificHeat, adjustForEquipmentTemperature);
    }
}

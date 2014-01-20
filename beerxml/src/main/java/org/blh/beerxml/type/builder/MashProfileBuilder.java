package org.blh.beerxml.type.builder;

import java.util.List;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.MashStep;
import org.blh.core.unit.PH;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.weight.Kilograms;

/**
 * Builder for the MashProfile type.
 *
 * @author thinner
 */
public interface MashProfileBuilder extends Builder<MashProfile> {

    MashProfileBuilder setAdjustForEquipmentTemperature(boolean adjustForEquipmentTemperature);

    MashProfileBuilder setGrainTemperature(Celsius grainTemperature);

    MashProfileBuilder setMashSteps(List<MashStep> mashSteps);

    MashProfileBuilder setName(String name);

    MashProfileBuilder setNotes(String notes);

    MashProfileBuilder setSpargePH(PH spargePH);

    MashProfileBuilder setSpargeTemperature(Celsius spargeTemperature);

    MashProfileBuilder setTunSpecificHeat(double tunSpecificHeat);

    MashProfileBuilder setTunTemperature(Celsius tunTemperature);

    MashProfileBuilder setTunWeight(Kilograms tunWeight);

}

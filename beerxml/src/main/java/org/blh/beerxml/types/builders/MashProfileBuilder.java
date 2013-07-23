/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.MashProfile;
import org.blh.beerxml.types.MashStep;
import org.blh.core.units.PH;
import org.blh.core.units.temperature.Celsius;
import org.blh.core.units.weight.Kilograms;
import java.util.List;

/**
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

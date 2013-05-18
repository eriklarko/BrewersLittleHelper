/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.MashStep;
import blh.core.units.PH;
import blh.core.units.temperature.Celcius;
import blh.core.units.weight.Kilograms;
import java.util.List;

/**
 *
 * @author thinner
 */
public interface MashProfileBuilder extends Builder<MashProfile> {

    MashProfileBuilder setAdjustForEquipmentTemperature(boolean adjustForEquipmentTemperature);

    MashProfileBuilder setGrainTemperature(Celcius grainTemperature);

    MashProfileBuilder setMashSteps(List<MashStep> mashSteps);

    MashProfileBuilder setName(String name);

    MashProfileBuilder setNotes(String notes);

    MashProfileBuilder setSpargePH(PH spargePH);

    MashProfileBuilder setSpargeTemperature(Celcius spargeTemperature);

    MashProfileBuilder setTunSpecificHeat(double tunSpecificHeat);

    MashProfileBuilder setTunTemperature(Celcius tunTemperature);

    MashProfileBuilder setTunWeight(Kilograms tunWeight);
    
}

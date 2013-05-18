/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Water;
import blh.core.units.PH;
import blh.core.units.PPM;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public interface WaterBuilder extends Builder<Water> {

    WaterBuilder setAmount(Liters amount);

    WaterBuilder setBicarbonate(PPM bicarbonate);

    WaterBuilder setCalcium(PPM calcium);

    WaterBuilder setChloride(PPM chloride);

    WaterBuilder setMagnesium(PPM magnesium);

    WaterBuilder setName(String name);

    WaterBuilder setNotes(String notes);

    WaterBuilder setPh(PH ph);

    WaterBuilder setSodium(PPM sodium);

    WaterBuilder setSulfate(PPM sulfate);
    
}

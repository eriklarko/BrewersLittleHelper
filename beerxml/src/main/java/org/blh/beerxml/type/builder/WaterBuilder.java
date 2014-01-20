package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Water;
import org.blh.core.unit.PH;
import org.blh.core.unit.PPM;
import org.blh.core.unit.volume.Liters;

/**
 * Builder for the Water type.
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

package org.blh.core.formulas.color;

import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.color.MaltColorUnit;
import org.blh.core.units.volume.USGallons;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class MaltColorUnitFormula  {

    public MaltColorUnit calc(ColorPotential totalColorPotential, Liters finalVolume) {
        return new MaltColorUnit(totalColorPotential, new USGallons(finalVolume));
    }

}

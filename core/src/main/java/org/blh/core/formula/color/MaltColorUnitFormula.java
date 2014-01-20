package org.blh.core.formula.color;

import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.color.MaltColorUnit;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.volume.USGallons;

/**
 * Calculates the MCU.
 *
 * http://morebeer.com/brewingtechniques/library/backissues/issue2.1/manning.html
 *
 * @author thinner
 */
public class MaltColorUnitFormula  {

    public MaltColorUnit calc(ColorPotential totalColorPotential, Liters finalVolume) {
        return new MaltColorUnit(totalColorPotential, new USGallons(finalVolume));
    }
}

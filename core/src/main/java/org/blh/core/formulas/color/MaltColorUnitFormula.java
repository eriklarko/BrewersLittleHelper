package org.blh.core.formulas.color;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.color.MaltColorUnit;
import org.blh.core.units.volume.USGallons;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class MaltColorUnitFormula implements Formula<MaltColorUnit> {

    @Override
    public MaltColorUnit calc(FullContext context) {
        Liters finalVolume = context.volumePost(context.FINAL);
        ColorPotential totalColorPotential = context.totalColorPotential.value();
        
        return calc(totalColorPotential, finalVolume);
    }

    public MaltColorUnit calc(ColorPotential totalColorPotential, Liters finalVolume) {
        return new MaltColorUnit(totalColorPotential, new USGallons(finalVolume));
    }

}

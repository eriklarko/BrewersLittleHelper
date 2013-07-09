package blh.core.formulas.color;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.color.ColorPotential;
import blh.core.units.color.MaltColorUnit;
import blh.core.units.volume.USGallons;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class MaltColorUnitFormula implements Formula<MaltColorUnit> {

    @Override
    public MaltColorUnit calc(FullContext context) {
        Liters finalVolume = context.finalVolume.value();
        ColorPotential totalColorPotential = context.totalColorPotential.value();
        
        return calc(totalColorPotential, finalVolume);
    }

    public MaltColorUnit calc(ColorPotential totalColorPotential, Liters finalVolume) {
        return new MaltColorUnit(totalColorPotential, new USGallons(finalVolume));
    }

}

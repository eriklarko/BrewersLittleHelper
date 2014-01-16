package org.blh.core.units.color;

import org.blh.core.units.NumericalUnit;
import org.blh.core.units.volume.USGallons;

/**
 *
 * @author thinner
 */
public class MaltColorUnit extends NumericalUnit {

    public MaltColorUnit(ColorPotential potential, USGallons finalVolume) {
        super(potential.value() / finalVolume.value());
    }
}

package org.blh.core.units.color;

import org.blh.core.units.DoubleUnit;
import org.blh.core.units.volume.USGallons;

/**
 *
 * @author thinner
 */
public class MaltColorUnit extends DoubleUnit {

    public MaltColorUnit(ColorPotential potential, USGallons finalVolume) {
        super(potential.value() / finalVolume.value());
    }
}

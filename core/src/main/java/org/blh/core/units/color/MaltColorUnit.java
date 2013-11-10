package org.blh.core.units.color;

import org.blh.core.units.NumericUnit;
import org.blh.core.units.volume.USGallons;

/**
 *
 * @author thinner
 */
public class MaltColorUnit extends NumericUnit {

    public MaltColorUnit(ColorPotential potential, USGallons finalVolume) {
        super(potential.value().divide(finalVolume.value()));
    }
}

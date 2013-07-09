package blh.core.units.color;

import blh.core.units.Unit;
import blh.core.units.volume.USGallons;

/**
 *
 * @author thinner
 */
public class MaltColorUnit extends Unit<Double> {

    public MaltColorUnit(ColorPotential potential, USGallons finalVolume) {
        super(potential.value() / finalVolume.value());
    }
}

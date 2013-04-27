package blh.core.units.color;

import blh.core.units.volume.Gallons;

/**
 *
 * @author thinner
 */
public class MaltColorUnit {
    private ColorPotential potential;
    private Gallons finalVolume;

    public MaltColorUnit(ColorPotential potential, Gallons finalVolume) {
        this.potential = potential;
        this.finalVolume = finalVolume;
    }
    
    public double value() {
        return potential.value() / finalVolume.value();
    }
}

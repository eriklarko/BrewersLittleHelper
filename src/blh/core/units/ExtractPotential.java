package blh.core.units;

import blh.core.units.gravity.GravityPoints;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class ExtractPotential extends Unit<Double> {

    public ExtractPotential(GravityPoints gravityPoints, Kilograms weight) {
        super(gravityPoints.value() / weight.value());
    }
}

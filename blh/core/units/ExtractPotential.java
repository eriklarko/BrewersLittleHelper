package blh.core.units;

import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class ExtractPotential extends Unit<Double> {

    public ExtractPotential(int gravityPoints, Kilograms weight) {
        super(gravityPoints / weight.value());
    }
}

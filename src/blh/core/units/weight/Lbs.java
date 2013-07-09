package blh.core.units.weight;

import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Lbs extends Unit<Double> {
    public static final double CONVERSION_FACTOR = 2.20462262;

    public Lbs(double value) {
        super(value);
    }

    public Lbs(Kilograms kgs) {
        super(kgs.value() * CONVERSION_FACTOR);
    }

    public Kilograms toKilograms() {
        return new Kilograms(this.value() / CONVERSION_FACTOR);
    }
}
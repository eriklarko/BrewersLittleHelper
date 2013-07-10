package blh.core.units.weight;

import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Oz extends Unit<Double> {
    public static final double CONVERSION_FACTOR = 0.0352739619;

    public Oz(double value) {
        super(value);
    }

    public Oz(Grams grams) {
        super(grams.value() * CONVERSION_FACTOR);
    }

    public Grams toGrams() {
        return new Grams(this.value() / CONVERSION_FACTOR);
    }
}
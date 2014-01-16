package org.blh.core.units.weight;

import org.blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Grams extends Unit<Double> {

    public static final int CONVERSION_FACTOR = 1000;

    public Grams(double value) {
        super(value);
    }

    public Grams(Kilograms kg) {
        super(kg.value() / CONVERSION_FACTOR);
    }

    public Kilograms toKilograms() {
        return new Kilograms(this.value() / CONVERSION_FACTOR);
    }
}

package org.blh.core.unit.weight;

import org.blh.core.unit.DoubleUnit;

/**
 * Ounces.
 * 
 * @author thinner
 */
public class Oz extends DoubleUnit {

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

	@Override
	public Oz deriveNew(double d) {
		return new Oz(d);
	}
}
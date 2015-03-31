package org.blh.core.unit.weight;

import org.blh.core.unit.DoubleUnit;

/**
 * Unit of mass.
 * 
 * @author thinner
 */
public class Grams extends DoubleUnit {

    public static final int CONVERSION_FACTOR = 1000;

    public Grams(double value) {
        super(value);
    }

    public Grams(Kilograms kg) {
        super(kg.value() * CONVERSION_FACTOR);
    }

    public Kilograms toKilograms() {
        return new Kilograms(this.value() / CONVERSION_FACTOR);
    }

	@Override
	public Grams deriveNew(double d) {
		return new Grams(d);
	}
}

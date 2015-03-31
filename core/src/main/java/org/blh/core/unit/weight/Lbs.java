package org.blh.core.unit.weight;

import org.blh.core.unit.DoubleUnit;

/**
 * Pounds.
 *
 * @author thinner
 */
public class Lbs extends DoubleUnit {

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

	@Override
	public Lbs deriveNew(double d) {
		return new Lbs(d);
	}
}
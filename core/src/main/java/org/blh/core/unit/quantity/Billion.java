package org.blh.core.unit.quantity;

import org.blh.core.unit.DoubleUnit;

/**
 * The value is a number of billions, to get the "true" value use the trueValue
 * method.
 *
 * @author thinner
 * @since Jul 9, 2013 11:42:05 PM
 */
public class Billion extends DoubleUnit {

    public static final int BILLON = 1_000_000_000;

    public Billion(double billion) {
        super(billion);
    }

    public double trueValue() {
        return this.value() * BILLON;
    }

	@Override
	public Billion deriveNew(double d) {
		return new Billion(d);
	}
}

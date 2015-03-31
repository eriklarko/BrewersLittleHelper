package org.blh.core.unit.quantity;

import org.blh.core.unit.DoubleUnit;

/**
 * The value is a number of millions, to get the "true" value use the trueValue
 * method.
 *
 * @author thinner
 * @since Jul 9, 2013 11:59:32 PM
 */
public class Million extends DoubleUnit {

    public static final int MILLION = 1_000_000;

    public Million(double million) {
        super(million);
    }

    public double trueValue() {
        return this.value() * MILLION;
    }

	@Override
	public Million deriveNew(double d) {
		return new Million(d);
	}
}

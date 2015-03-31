package org.blh.core.unit;

/**
 * On the form xy.zw%. For 0.xyzw, use Factor.
 *
 * @author thinner
 */
public class Percentage extends DoubleUnit {

    public static final int CONVERSION_FACTOR = 100;

    public Percentage(double value) {
        super(value);
    }

    public Percentage(Factor value) {
        super(value.value() * CONVERSION_FACTOR);
    }

    public Factor asFactor() {
        return new Factor(this.value() / CONVERSION_FACTOR);
    }

	@Override
	public Percentage deriveNew(double d) {
		return new Percentage(d);
	}
}

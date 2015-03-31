package org.blh.core.unit.color;

import org.blh.core.unit.DoubleUnit;

/**
 * EBC = 1.97 * SRM -> SRM = EBC / 1.97
 *
 * @author thinner
 */
public class SRM extends DoubleUnit {

    public static final double CONVERSION_FACTOR = 1.97;

    public SRM(double value) {
        super(value);
    }

    public SRM(EBC ebc) {
        super(ebc.value() / CONVERSION_FACTOR);
    }

    public EBC toEBC() {
        return new EBC(this.value() * CONVERSION_FACTOR);
    }

	@Override
	public SRM deriveNew(double d) {
		return new SRM(d);
	}
}

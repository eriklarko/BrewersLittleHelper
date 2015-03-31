package org.blh.core.unit.pressure;

import org.blh.core.unit.DoubleUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:05 AM
 */
public class PSI extends DoubleUnit {

    public static final double CONVERSION_FACTOR = 14.503773800;

    public PSI(double value) {
        super(value);
    }

    public PSI(Bar value) {
        super(value.value() * CONVERSION_FACTOR);
    }

    public Bar toBar() {
        return new Bar(this.value() / CONVERSION_FACTOR);
    }

	@Override
	public PSI deriveNew(double d) {
		return new PSI(d);
	}
}

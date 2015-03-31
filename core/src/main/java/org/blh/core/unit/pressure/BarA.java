package org.blh.core.unit.pressure;

import org.blh.core.unit.DoubleUnit;

/**
 * 1 bar = 2 barA
 *
 * Created by Erik Larkö at 5/29/13 2:13 PM
 */
public class BarA extends DoubleUnit {

    public BarA(double value) {
        super(value);
    }

    public BarA(Bar value) {
        super(value.value() + 1);
    }

    public Bar toBar() {
        return new Bar(this.value() - 1);
    }

	@Override
	public BarA deriveNew(double d) {
		return new BarA(d);
	}
}

package org.blh.core.unit.pressure;

import org.blh.core.unit.DoubleUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:04 AM
 */
public class Bar extends DoubleUnit {

    public Bar(double value) {
        super(value);
    }

	@Override
	public Bar deriveNew(double d) {
		return new Bar(d);
	}
}

package org.blh.core.unit.time;

import org.blh.core.unit.DoubleUnit;

/**
 * Created by Erik Larkö at 6/23/13 4:33 PM
 */
public class Seconds extends DoubleUnit {

    public Seconds(double value) {
        super(value);
    }

	@Override
	public Seconds deriveNew(double d) {
		return new Seconds(d);
	}
}

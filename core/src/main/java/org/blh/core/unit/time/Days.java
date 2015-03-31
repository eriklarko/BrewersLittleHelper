package org.blh.core.unit.time;

import org.blh.core.unit.DoubleUnit;

/**
 * The value holds a number of days.
 * 
 * @author thinner
 */
public class Days extends DoubleUnit {

    public Days(double value) {
        super(value);
    }

	@Override
	public Days deriveNew(double d) {
		return new Days(d);
	}
}

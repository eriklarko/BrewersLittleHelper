package org.blh.core.unit.time;

import org.blh.core.unit.DoubleUnit;

/**
 * The value holds a number of minutes.
 * 
 * @author thinner
 */
public class Minutes extends DoubleUnit {

    public Minutes(double value) {
        super(value);
    }

	@Override
	public Minutes deriveNew(double d) {
		return new Minutes(d);
	}
}

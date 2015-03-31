package org.blh.core.unit.distance;

import org.blh.core.unit.DoubleUnit;

/**
 * SI unit of distance.
 * 
 * @author thinner
 */
public class Meters extends DoubleUnit {

    public Meters(double value) {
        super(value);
    }

	@Override
	public Meters deriveNew(double d) {
		return new Meters(d);
	}
}

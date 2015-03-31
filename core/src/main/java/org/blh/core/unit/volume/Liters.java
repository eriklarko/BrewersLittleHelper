package org.blh.core.unit.volume;

import org.blh.core.unit.DoubleUnit;

/**
 * SI unit of volume.
 * 
 * @author thinner
 */
public class Liters extends DoubleUnit {

    public Liters(double value) {
        super(value);
    }

	@Override
	public Liters deriveNew(double d) {
		return new Liters(d);
	}
}

package org.blh.core.unit.temperature;

import org.blh.core.unit.DoubleUnit;

/**
 * SI unit of temperature.
 * 
 * @author thinner
 */
public class Celsius extends DoubleUnit {

    public Celsius(double value) {
        super(value);
    }

	@Override
	public Celsius deriveNew(double d) {
		return new Celsius(d);
	}
}

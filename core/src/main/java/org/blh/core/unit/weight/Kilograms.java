package org.blh.core.unit.weight;

import org.blh.core.unit.DoubleUnit;

/**
 * SI unit of mass.
 * 
 * @author thinner
 */
public class Kilograms extends DoubleUnit {

    public Kilograms(double value) {
        super(value);
    }

	@Override
	public Kilograms deriveNew(double d) {
		return new Kilograms(d);
	}
}

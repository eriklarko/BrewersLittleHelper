package org.blh.core.unit.gravity;

import org.blh.core.unit.DoubleUnit;

/**
 * Specific gravity is the ratio of the density of a substance to the density
 * (mass of the same unit volume) of a reference substance. In most cases, water.
 *
 * @author thinner
 */
public class SpecificGravity extends DoubleUnit {

    public SpecificGravity(double value) {
        super(value);
    }

	@Override
	public SpecificGravity deriveNew(double d) {
		return new SpecificGravity(d);
	}
}

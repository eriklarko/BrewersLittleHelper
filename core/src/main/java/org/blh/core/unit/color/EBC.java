package org.blh.core.unit.color;

import org.blh.core.unit.DoubleUnit;

/**
 * EBC = 1.97 * SRM
 *
 * @author thinner
 */
public class EBC extends DoubleUnit {

    public EBC(double value) {
        super(value);
    }

	@Override
	public EBC deriveNew(double d) {
		return new EBC(d);
	}
}

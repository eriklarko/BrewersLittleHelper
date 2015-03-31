package org.blh.core.unit.bitterness;

import org.blh.core.unit.DoubleUnit;

/**
 * Used to describe the bitterness in beer. Homebrewer's bitterness unit.
 * Usually IBUs are preferred.
 *
 * @author thinner
 */
public class HBU extends DoubleUnit {

    public HBU(double value) {
        super(value);
    }

	@Override
	public HBU deriveNew(double d) {
		return new HBU(d);
	}
}

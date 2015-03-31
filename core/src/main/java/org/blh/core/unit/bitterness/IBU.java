package org.blh.core.unit.bitterness;

import org.blh.core.unit.DoubleUnit;

/**
 * Used to describe the bitterness in beer. International bitterness units.
 * This is the default bitterness unit.
 *
 * @author thinner
 */
public class IBU extends DoubleUnit {

    public IBU(double value) {
        super(value);
    }

	@Override
	public IBU deriveNew(double d) {
		return new IBU(d);
	}
}

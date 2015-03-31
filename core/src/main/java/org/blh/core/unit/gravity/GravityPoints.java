package org.blh.core.unit.gravity;

import org.blh.core.unit.DoubleUnit;

/**
 * Created by Erik Larkö at 7/2/13 10:55 PM
 */
public class GravityPoints extends DoubleUnit {

    public GravityPoints(double value) {
        super(value);
    }

    public GravityPoints(SpecificGravity sg) {
        this((sg.value() - 1) * 1000);
    }

    public SpecificGravity toSpecificGravity() {
        return new SpecificGravity(this.value() / 1000 + 1);
    }

	@Override
	public GravityPoints deriveNew(double d) {
		return new GravityPoints(d);
	}
}

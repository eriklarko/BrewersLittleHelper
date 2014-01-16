package org.blh.core.units.gravity;

import org.blh.core.units.Unit;

/**
 * Created by Erik Larkö at 7/2/13 10:55 PM
 */
public class GravityPoints extends Unit<Double> {

    public GravityPoints(double value) {
        super(value);
    }

    public GravityPoints(SpecificGravity sg) {
        this((sg.value() - 1) * 1000);
    }

    public SpecificGravity toSpecificGravity() {
        return new SpecificGravity(this.value() / 1000 + 1);
    }
}

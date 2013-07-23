package org.blh.core.units.pressure;

import org.blh.core.units.Unit;

/**
 * Created by Erik Larkö at 5/28/13 7:05 AM
 */
public class PSI extends Unit<Double> {
    public static final double CONVERSION_UNIT = 14.503773800;

    public PSI(double value) {
        super(value);
    }

    public PSI(Bar value) {
        super(value.value() * CONVERSION_UNIT);
    }

    public Bar toBar() {
        return new Bar(value / CONVERSION_UNIT);
    }
}

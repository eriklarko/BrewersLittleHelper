package org.blh.core.units.pressure;

import org.blh.core.units.DoubleUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:05 AM
 */
public class PSI extends DoubleUnit {

    public static final double CONVERSION_FACTOR = 14.503773800;

    public PSI(double value) {
        super(value);
    }

    public PSI(Bar value) {
        super(value.value() * CONVERSION_FACTOR);
    }

    public Bar toBar() {
        return new Bar(this.value() / CONVERSION_FACTOR);
    }
}

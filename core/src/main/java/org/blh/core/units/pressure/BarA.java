package org.blh.core.units.pressure;

import org.blh.core.units.Unit;

/**
 * 1 bar = 2 barA
 *
 * Created by Erik Larkö at 5/29/13 2:13 PM
 */
public class BarA extends Unit<Double> {

    public BarA(double value) {
        super(value);
    }

    public BarA(Bar value) {
        super(value.value() + 1);
    }

    public Bar toBar() {
        return new Bar(this.value() - 1);
    }
}

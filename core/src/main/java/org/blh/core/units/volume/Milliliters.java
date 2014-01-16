package org.blh.core.units.volume;

import org.blh.core.units.Unit;

/**
 * Created by Erik Larkö at 7/4/13 11:00 PM
 */
public class Milliliters extends Unit<Double> {

    public static final int CONVERSION_FACTOR = 1000;

    public Milliliters(double value) {
        super(value);
    }

    public Milliliters(Liters liters) {
        super(liters.value() * CONVERSION_FACTOR);
    }

    public Liters toLiters() {
        return new Liters(this.value() / CONVERSION_FACTOR);
    }
}

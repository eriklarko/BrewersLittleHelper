package org.blh.core.unit.volume;

import org.blh.core.unit.DoubleUnit;

/**
 * Created by Erik Larkö at 7/4/13 11:00 PM
 */
public class Milliliters extends DoubleUnit {

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

	@Override
	public Milliliters deriveNew(double d) {
		return new Milliliters(d);
	}
}

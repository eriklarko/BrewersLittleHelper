package org.blh.core.unit.temperature;

import org.blh.core.unit.DoubleUnit;

/**
 * F = 9/5 C + 32 C = (F - 32) / (9/5) = 5(F - 32) / 9 = 5/9 * (F - 32)
 *
 * Created by Erik Larkö at 5/28/13 7:06 AM
 */
public class Fahrenheit extends DoubleUnit {

    public Fahrenheit(double value) {
        super(value);
    }

    public Fahrenheit(Celsius celsius) {
        super((9 * celsius.value()) / 5 + 32);
    }

    public Celsius toCelsius() {
        return new Celsius((5d / 9) * (this.value() - 32));
    }

	@Override
	public Fahrenheit deriveNew(double d) {
		return new Fahrenheit(d);
	}
}

package org.blh.core.units.distance;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * 1 meter = 3.2808399 feet
 *
 * Created by Erik Larkö at 6/23/13 4:28 PM
 */
public class Feet extends NumericalUnit {

    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(3.2808399);

    public Feet(double value) {
        super(value);
    }

    public Feet(Meters value) {
        super(value.value().multiply(CONVERSION_FACTOR));
    }

    public Meters toMeters() {
        return new Meters(this.value.divide(CONVERSION_FACTOR));
    }
}

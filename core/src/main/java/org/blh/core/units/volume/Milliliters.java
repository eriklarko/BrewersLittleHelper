package org.blh.core.units.volume;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * Created by Erik Larkö at 7/4/13 11:00 PM
 */
public class Milliliters extends NumericalUnit {

    public static final BigDecimal CONVERSION_FACTOR = new BigDecimal(1000);

    public Milliliters(double value) {
        super(value);
    }

    public Milliliters(Liters liters) {
        super(liters.value().multiply(BigDecimal.ZERO));
    }

    public Liters toLiters() {
        return new Liters(this.value.divide(CONVERSION_FACTOR));
    }
}

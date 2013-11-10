package org.blh.core.units.temperature;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * F = 9/5 C + 32 C = (F - 32) / (9/5) = 5(F - 32) / 9 = 5/9 * (F - 32)
 *
 * Created by Erik Larkö at 5/28/13 7:06 AM
 */
public class Fahrenheit extends NumericUnit {

    public static final BigDecimal FIVE = BigDecimal.valueOf(5);
    public static final BigDecimal NINE = BigDecimal.valueOf(9);
    public static final BigDecimal THRITY_TWO = BigDecimal.valueOf(32);

    public Fahrenheit(BigDecimal value) {
        super(value);
    }

    public Fahrenheit(double value) {
        super(value);
    }

    public Fahrenheit(Celsius celsius) {
        super((NINE.multiply(celsius.value())).divide(FIVE).add(THRITY_TWO));
    }

    public Celsius toCelsius() {
        return new Celsius((FIVE.divide(NINE)).multiply(this.value.subtract(THRITY_TWO)));
    }
}

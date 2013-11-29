package org.blh.core.units.weight;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 */
public class Grams extends NumericalUnit {

    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(1000);

    public Grams(BigDecimal value) {
        super(value);
    }

    public Grams(double value) {
        super(value);
    }

    public Grams(Kilograms kg) {
        super(kg.value().multiply(CONVERSION_FACTOR));
    }

    public Kilograms toKilograms() {
        return new Kilograms(this.value.divide(CONVERSION_FACTOR));
    }
}

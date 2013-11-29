package org.blh.core.units.weight;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 */
public class Lbs extends NumericalUnit {
    public static final BigDecimal CONVERSION_FACTOR = new BigDecimal(2.20462262);

    public Lbs(double value) {
        super(value);
    }

    public Lbs(Kilograms kgs) {
        super(kgs.value().multiply(CONVERSION_FACTOR));
    }

    public Kilograms toKilograms() {
        return new Kilograms(this.value().divide(CONVERSION_FACTOR));
    }
}
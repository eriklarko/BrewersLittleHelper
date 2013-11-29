package org.blh.core.units.weight;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 */
public class Oz extends NumericalUnit {
    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(0.0352739619);

    public Oz(double value) {
        super(value);
    }

    public Oz(Grams grams) {
        super(grams.value().multiply(CONVERSION_FACTOR));
    }

    public Grams toGrams() {
        return new Grams(this.value().divide(CONVERSION_FACTOR));
    }
}
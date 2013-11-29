package org.blh.core.units;

import java.math.BigDecimal;

/**
 * On the form xy.zw%. For 0.xyzw, use Factor.
 *
 * @author thinner
 */
public class Percentage extends NumericalUnit {

    public static final BigDecimal CONVERSION_FACTOR = new BigDecimal(100);

    public Percentage(BigDecimal value) {
        super(value);
    }

    public Percentage(double value) {
        super(value);
    }

    public Percentage(Factor value) {
        super(value.value().multiply(CONVERSION_FACTOR));
    }

    public Factor asFactor() {
        return new Factor(this.value.divide(CONVERSION_FACTOR));
    }
}

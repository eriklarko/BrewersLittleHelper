package org.blh.core.units;

import java.math.BigDecimal;

/**
 * On the form 0.xyzw. For xy.zw% use Percentage.
 * @author thinner
 */
public class Factor extends NumericUnit {

    public static final BigDecimal CONVERSION_FACTOR = new BigDecimal(100);

    public Factor(BigDecimal value) {
        super(value);
    }

    public Factor(BigDecimal value) {
        super(value);
    }

    public Factor(double value) {
        super(value);
    }

    public Factor(Percentage value) {
        super(value.value().divide(CONVERSION_FACTOR));
    }

    public Percentage asPercentage() {
        return new Percentage(this.value.multiply(CONVERSION_FACTOR));
    }
}

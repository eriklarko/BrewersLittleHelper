package org.blh.core.units;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 31, 2013 7:32:31 PM
 */
public class NumericUnit extends Unit<BigDecimal> {

    public NumericUnit(BigDecimal value) {
        super(value);
    }

    public NumericUnit(double value) {
        this(new BigDecimal(value, new MathContext(100)));
    }

    public double inexactValue() {
        return this.value.doubleValue();
    }
}

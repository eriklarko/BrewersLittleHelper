package org.blh.core.units;

import java.math.BigDecimal;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 31, 2013 7:32:31 PM
 */
public class NumericalUnit extends Unit<BigDecimal> {

    public NumericalUnit(BigDecimal value) {
        super(value);
    }

    public NumericalUnit(double value) {
        //this(new BigDecimal(value));
        this(BigDecimal.valueOf(value));
    }

    public double inexactValue() {
        return this.value.doubleValue();
    }
}

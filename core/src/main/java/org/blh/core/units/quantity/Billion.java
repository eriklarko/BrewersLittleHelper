package org.blh.core.units.quantity;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 * @since Jul 9, 2013 11:42:05 PM
 */
public class Billion extends NumericalUnit {

    public static final BigDecimal BILLON = new BigDecimal(1_000_000_000);

    public Billion(BigDecimal billion) {
        super(billion);
    }

    public Billion(double billion) {
        super(billion);
    }

    public BigDecimal trueValue() {
        return this.value.multiply(BILLON);
    }
}

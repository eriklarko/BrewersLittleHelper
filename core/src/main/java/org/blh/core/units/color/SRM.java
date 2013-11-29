package org.blh.core.units.color;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * EBC = 1.97 * SRM -> SRM = EBC / 1.97
 *
 * @author thinner
 */
public class SRM extends NumericalUnit {

    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(1.97);

    public SRM(BigDecimal value) {
        super(value);
    }

    public SRM(double value) {
        super(value);
    }

    public SRM(EBC ebc) {
        super(ebc.value().divide(CONVERSION_FACTOR));
    }

    public EBC toEBC() {
        return new EBC(this.value.multiply(CONVERSION_FACTOR));
    }
}

package org.blh.core.units.alcohol;

import java.math.BigDecimal;
import org.blh.core.units.Factor;
import org.blh.core.units.Unit;

/**
 * ABW = ABV * 0.8 = 4/5 * ABV
 *
 * ABV = ABW / 0.8 = ABW / (4/5) = 5 ABW / 4 = 5/4 * ABW
 *
 * Created by Erik Larkö at 5/28/13 7:11 AM
 */
public class ABW extends Unit<Factor> {

    public static final BigDecimal FROM_ABV_CONVERSION_FACTOR = BigDecimal.valueOf(0.8);
    public static final BigDecimal TO_ABV_CONVERSION_FACTOR = BigDecimal.valueOf(1.25);

    public ABW(Factor value) {
        super(value);
    }

    public ABW(BigDecimal value) {
        super(new Factor(value));
    }

    public ABW(double value) {
        super(new Factor(value));
    }

    public ABW(ABV abv) {
        this(abv.value().value().multiply(FROM_ABV_CONVERSION_FACTOR));
    }

    public ABV toABV() {
        return new ABV(this.value().value().multiply(TO_ABV_CONVERSION_FACTOR));
    }
}

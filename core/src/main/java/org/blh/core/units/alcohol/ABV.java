package org.blh.core.units.alcohol;

import java.math.BigDecimal;
import org.blh.core.units.Factor;
import org.blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class ABV extends Unit<Factor> {

    public ABV(Factor value) {
        super(value);
    }

    public ABV(BigDecimal value) {
        super(new Factor(value));
    }

    public ABV(double value) {
        super(new Factor(value));
    }
}

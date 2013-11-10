package org.blh.core.units.color;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * EBC = 1.97 * SRM
 *
 * @author thinner
 */
public class EBC extends NumericUnit {

    public EBC(BigDecimal value) {
        super(value);
    }

    public EBC(double value) {
        super(value);
    }
}

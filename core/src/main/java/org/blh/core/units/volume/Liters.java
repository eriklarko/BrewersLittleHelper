package org.blh.core.units.volume;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 *
 * @author thinner
 */
public class Liters extends NumericUnit {

    public Liters(BigDecimal value) {
        super(value);
    }

    public Liters(double value) {
	super(value);
    }
}

package org.blh.core.units.volume;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 */
public class Liters extends NumericalUnit {

    public Liters(BigDecimal value) {
        super(value);
    }

    public Liters(double value) {
	super(value);
    }
}

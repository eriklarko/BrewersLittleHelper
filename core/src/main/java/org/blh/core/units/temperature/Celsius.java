package org.blh.core.units.temperature;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 */
public class Celsius extends NumericalUnit {

    public Celsius(BigDecimal value) {
        super(value);
    }

    public Celsius(double value) {
        super(value);
    }
}

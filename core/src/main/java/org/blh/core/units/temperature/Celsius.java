package org.blh.core.units.temperature;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 *
 * @author thinner
 */
public class Celsius extends NumericUnit {

    public Celsius(BigDecimal value) {
        super(value);
    }

    public Celsius(double value) {
        super(value);
    }
}

package org.blh.core.unit.alcohol;

import org.blh.core.unit.Factor;
import org.blh.core.unit.Unit;

/**
 * Alcohol by volume. This is the default measurement of alcohol.
 *
 * @author thinner
 */
public class ABV extends Unit<Factor> {

    public ABV(Factor value) {
        super(value);
    }

    public ABV(double value) {
        super(new Factor(value));
    }
}

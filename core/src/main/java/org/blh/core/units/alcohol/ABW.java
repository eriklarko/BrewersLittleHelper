package org.blh.core.units.alcohol;

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

    public ABW(Factor value) {
        super(value);
    }

    public ABW(double value) {
        super(new Factor(value));
    }

    public ABW(ABV abv) {
        this(abv.value().value() * 0.8);
    }

    public ABV toABV() {
        return new ABV(1.25 * this.value().value());
    }
}

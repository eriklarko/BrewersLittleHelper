package org.blh.core.units.quantity;

import org.blh.core.units.DoubleUnit;

/**
 *
 * @author thinner
 * @since Jul 9, 2013 11:59:32 PM
 */
public class Million extends DoubleUnit {

    public static final int MILLION = 1_000_000;

    public Million(double million) {
        super(million);
    }

    public double trueValue() {
        return this.value() * MILLION;
    }
}

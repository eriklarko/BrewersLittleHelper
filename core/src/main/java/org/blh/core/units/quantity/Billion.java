package org.blh.core.units.quantity;

import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 * @since Jul 9, 2013 11:42:05 PM
 */
public class Billion extends NumericalUnit {

    public static final int BILLON = 1_000_000_000;

    public Billion(double billion) {
        super(billion);
    }

    public double trueValue() {
        return this.value() * BILLON;
    }
}

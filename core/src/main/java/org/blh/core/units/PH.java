package org.blh.core.units;

/**
 *
 * @author thinner
 */
public class PH extends NumericUnit {

    public PH(double value) {
        super(value);
        if (value < 0 || value > 14) {
            throw new IllegalArgumentException("You craaaazzzy. PH is 0-14");
        }
    }
}

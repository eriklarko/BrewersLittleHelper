package org.blh.core.units;

/**
 * On the form 0.xyzw. For xy.zw% use Percentage.
 * @author thinner
 */
public class Factor extends Unit<Double> {

    public static final int CONVERSION_FACTOR = 100;

    public Factor(double value) {
        super(value);
    }

    public Factor(Percentage value) {
        super(value.value() / CONVERSION_FACTOR);
    }

    public Percentage asPercentage() {
        return new Percentage(this.value() * CONVERSION_FACTOR);
    }
}

package org.blh.core.units.volume;

import org.blh.core.units.DoubleUnit;

/**
 *
 * @author thinner
 */
public class USGallons extends DoubleUnit {

    public static final double CONVERSION_FACTOR = 0.264172;

    public USGallons(double value) {
        super(value);
    }

    public USGallons(Liters liters) {
        super(liters.value() * CONVERSION_FACTOR);
    }

    public Liters toLiters() {
        return new Liters(this.value() / CONVERSION_FACTOR);
    }
}

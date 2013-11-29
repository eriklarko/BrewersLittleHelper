package org.blh.core.units.volume;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 *
 * @author thinner
 */
public class USGallons extends NumericalUnit {
    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(0.264172);

    public USGallons(double value) {
        super(value);
    }

    public USGallons(Liters liters) {
        super(liters.value().multiply(CONVERSION_FACTOR));
    }

    public Liters toLiters() {
        return new Liters(this.value().divide(CONVERSION_FACTOR));
    }
}

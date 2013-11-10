package org.blh.core.units.distance;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * 1 meter = 39.3700787 inches
 * 
 * Created by Erik Larkö at 6/23/13 4:32 PM
 */
public class Inch extends NumericUnit {

    public static final BigDecimal CONVERSION_FACTOR = BigDecimal.valueOf(39.3700787);

    public Inch(double value) {
        super(value);
    }
    
    public Inch(Meters value) {
        super(value.value().multiply(CONVERSION_FACTOR));
    }
    
    public Meters toMeters() {
        return new Meters(this.value.divide(CONVERSION_FACTOR));
    }
}

package org.blh.core.units.quantity;

import org.blh.core.units.Unit;
import java.math.BigDecimal;

/**
 * 
 * @author thinner
 * @since Jul 9, 2013 11:59:32 PM
 */
public class Million extends Unit<Double> {
    
    public static BigDecimal MILLION = new BigDecimal(1_000_000);

    public Million(double value) {
        super(value);
    }
    
    public BigDecimal trueValue() {
        BigDecimal base = new BigDecimal(this.value);
        return base.multiply(MILLION);
    }
}

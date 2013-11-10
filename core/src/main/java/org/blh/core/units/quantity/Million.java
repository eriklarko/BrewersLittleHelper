package org.blh.core.units.quantity;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * 
 * @author thinner
 * @since Jul 9, 2013 11:59:32 PM
 */
public class Million extends NumericUnit {
    
    public static final BigDecimal MILLION = new BigDecimal(1_000_000);

    public Million(BigDecimal million) {
        super(million);
    }

    public Million(double million) {
        super(million);
    }
    
    public BigDecimal trueValue() {
        return this.value.multiply(MILLION);
    }
}

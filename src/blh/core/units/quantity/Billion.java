package blh.core.units.quantity;

import blh.core.units.Unit;
import java.math.BigDecimal;

/**
 * 
 * @author thinner
 * @since Jul 9, 2013 11:42:05 PM
 */
public class Billion extends Unit<Double> {

    public static BigDecimal BILLON = new BigDecimal(1_000_000_000);
    
    public Billion(double billions) {
        super(billions);
    }
    
    public BigDecimal trueValue() {
        BigDecimal base = new BigDecimal(this.value);
        return base.multiply(BILLON);
    }
}

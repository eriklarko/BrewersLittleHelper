package org.blh.core.units;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
<<<<<<< HEAD
 *
=======
 *
>>>>>>> Switched to BigDecimal
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Aug 6, 2013 10:43:42 PM
 */
public class TrollLargeFloat extends BigDecimal {

    public TrollLargeFloat(String val) {
        super(val);
    }

    public TrollLargeFloat(BigInteger val) {
        super(val);
    }

    public TrollLargeFloat(int val) {
        super(val);
    }
<<<<<<< HEAD

    public TrollLargeFloat
=======

    public TrollLargeFloat
>>>>>>> Switched to BigDecimal

    @Override
    public BigDecimal divide(BigDecimal divisor) {
        int sigificantDigits;
        return super.divide(divisor); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD


=======


>>>>>>> Switched to BigDecimal

}

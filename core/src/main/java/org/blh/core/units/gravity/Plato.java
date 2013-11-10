package org.blh.core.units.gravity;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * Taken from http://pintwell.com/2011/nov/02/calculate-specific-gravity-plato/ and
 *            http://plato.montanahomebrewers.org/
 *
 * {Plato/(258.6-([Plato/258.2]*227.1)}+1 = Specific gravity
 *
 * Created by Erik Larkö at 7/4/13 10:48 PM
 */
public class Plato extends NumericUnit {

    public static final BigDecimal FOUR = BigDecimal.valueOf(4);
    public static final BigDecimal _258_2 = BigDecimal.valueOf(258.2);
    public static final BigDecimal _258_6 = BigDecimal.valueOf(258.6);
    public static final BigDecimal _227_1 = BigDecimal.valueOf(227.1);

    public Plato(double value) {
        super(value);
    }

    public Plato(SpecificGravity gravity) {
        super((gravity.value().subtract(BigDecimal.ONE)).multiply(GravityPoints.ONE_THOUSAND).divide(FOUR));
    }

    public SpecificGravity toSpecificGravity() {
        BigDecimal a = this.value().divide(_258_2);
        a = a.multiply(_227_1);
        a = _258_6.subtract(a);
        a = this.value().divide(a);
        a = a.add(BigDecimal.ONE);
        return new SpecificGravity(a);
    }
}

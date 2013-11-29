package org.blh.core.units.gravity;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * Created by Erik Larkö at 7/2/13 10:55 PM
 */
public class GravityPoints extends NumericalUnit {

    public static final BigDecimal ONE_THOUSAND = new BigDecimal(1000);

    public GravityPoints(BigDecimal value) {
        super(value);
    }

    public GravityPoints(double value) {
        super(value);
    }

    public GravityPoints(SpecificGravity sg) {
        this((sg.value().subtract(BigDecimal.ONE)).multiply(ONE_THOUSAND));
    }

    public SpecificGravity toSpecificGravity() {
        return new SpecificGravity(this.value().divide(ONE_THOUSAND).add(BigDecimal.ONE));
    }
}

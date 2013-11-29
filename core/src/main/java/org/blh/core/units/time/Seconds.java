package org.blh.core.units.time;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * Created by Erik Larkö at 6/23/13 4:33 PM
 */
public class Seconds extends NumericalUnit {

    public Seconds(BigDecimal value) {
        super(value);
    }

    public Seconds(double value) {
        super(value);
    }
}

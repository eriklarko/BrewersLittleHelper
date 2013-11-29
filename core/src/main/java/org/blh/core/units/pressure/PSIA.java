package org.blh.core.units.pressure;

import java.math.BigDecimal;
import org.blh.core.units.NumericalUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:12 AM
 */
public class PSIA extends NumericalUnit {

    public PSIA(BigDecimal value) {
        super(value);
    }

    public PSIA(double value) {
        super(value);
    }

    public PSIA(PSI value) {
        super(value.value().add(PSI.CONVERSION_FACTOR));
    }

    public PSIA(Bar bar) {
        this(new PSI(bar));
    }

    public PSIA(BarA barA) {
        this(barA.toBar());
    }

    public PSI toPSI() {
        return new PSI(this.value.subtract(PSI.CONVERSION_FACTOR));
    }

    public Bar toBar() {
        return toPSI().toBar();
    }
}

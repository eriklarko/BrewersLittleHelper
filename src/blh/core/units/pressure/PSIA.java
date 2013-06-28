package blh.core.units.pressure;

import blh.core.units.Unit;

/**
 * Created by Erik Larkö at 5/28/13 7:12 AM
 */
public class PSIA extends Unit<Double> {
    public PSIA(double value) {
        super(value);
    }

    public PSIA(BarA bar) {
        super(bar.value() * PSI.CONVERSION_UNIT);
    }
}

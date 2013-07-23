package org.blh.core.units.pressure;

import org.blh.core.units.Unit;

/**
 * Created by Erik Larkö at 5/28/13 7:12 AM
 */
public class PSIA extends Unit<Double> {
    
    public PSIA(double value) {
        super(value);
    }
    
    public PSIA(PSI value) {
        super(value.value() + PSI.CONVERSION_UNIT);
    }
    
    public PSIA(Bar bar) {
        this(new PSI(bar));
    }

    public PSIA(BarA barA) {
        this(barA.toBar());
    }
    
    public PSI toPSI() {
        return new PSI(this.value - PSI.CONVERSION_UNIT);
    }
    
    public Bar toBar() {
        return toPSI().toBar();
    }
}

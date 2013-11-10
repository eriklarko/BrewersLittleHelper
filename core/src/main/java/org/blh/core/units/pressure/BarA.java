package org.blh.core.units.pressure;

import java.math.BigDecimal;
import org.blh.core.units.NumericUnit;

/**
 * 1 bar = 2 barA
 * 
 * Created by Erik Larkö at 5/29/13 2:13 PM
 */
public class BarA extends NumericUnit {

    public BarA(BigDecimal value) {
        super(value);
    }

    public BarA(double value) {
        super(value);
    }
    
    public BarA(Bar value) {
        super(value.value().add(BigDecimal.ONE));
    }
    
    public Bar toBar() {
        return new Bar(this.value.subtract(BigDecimal.ONE));
    }
}

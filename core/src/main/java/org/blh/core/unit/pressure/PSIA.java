package org.blh.core.unit.pressure;

import org.blh.core.unit.DoubleUnit;

/**
 * Created by Erik Larkö at 5/28/13 7:12 AM
 */
public class PSIA extends DoubleUnit {

    public PSIA(double value) {
        super(value);
    }

    public PSIA(PSI value) {
        super(value.value() + PSI.CONVERSION_FACTOR);
    }

    public PSIA(Bar bar) {
        this(new PSI(bar));
    }

    public PSIA(BarA barA) {
        this(barA.toBar());
    }

    public PSI toPSI() {
        return new PSI(this.value() - PSI.CONVERSION_FACTOR);
    }

    public Bar toBar() {
        return toPSI().toBar();
    }

	@Override
	public PSIA deriveNew(double d) {
		return new PSIA(d);
	}
}

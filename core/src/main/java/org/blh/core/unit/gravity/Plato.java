package org.blh.core.unit.gravity;

import org.blh.core.unit.DoubleUnit;

/**
 * Taken from http://pintwell.com/2011/nov/02/calculate-specific-gravity-plato/ and
 *            http://plato.montanahomebrewers.org/
 *
 * {Plato/(258.6-([Plato/258.2]*227.1)}+1 = Specific gravity
 *
 * Created by Erik Larkö at 7/4/13 10:48 PM
 */
public class Plato extends DoubleUnit {

    public Plato(double value) {
        super(value);
    }

    public Plato(SpecificGravity gravity) {
        super((gravity.value() - 1) * 1000 / 4);
    }

    public SpecificGravity toSpecificGravity() {
        double a = this.value() / 258.2;
        a = a * 227.1;
        a = 258.6 - a;
        a = this.value() / a;
        a = a + 1;
        return new SpecificGravity(a);
    }

	@Override
	public Plato deriveNew(double d) {
		return new Plato(d);
	}
}

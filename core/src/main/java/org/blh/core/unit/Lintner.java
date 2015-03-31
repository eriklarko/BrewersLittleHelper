package org.blh.core.unit;

/**
 * °Lintner or degrees Lintner is a unit used to measure the ability of a malt
 * to reduce starch to sugar, that is, its diastatic power.
 *
 * @author thinner
 */
public class Lintner extends DoubleUnit {

    public Lintner(double value) {
        super(value);
    }

	@Override
	public Lintner deriveNew(double d) {
		return new Lintner(d);
	}
}

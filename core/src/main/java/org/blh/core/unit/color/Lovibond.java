package org.blh.core.unit.color;

import org.blh.core.unit.DoubleUnit;

/**
 * "Degrees Lovibond" or "°L" scale is a measure of the colour of a substance,
 * usually beer, whiskey, or sugar solutions. The determination of the degrees
 * lovibond takes place by comparing the colour of the substance to a series of
 * amber to brown glass slides, usually by a colorimeter.
 *
 * http://homebrew.stackexchange.com/questions/2632/how-do-you-convert-between-srm-and-lovibond
 * Morey's Formula
 *   SRM = 1.4922 * (W * L / V) ^ .6859
 *
 * Daniels' Formula
 *   SRM = (.2 * W * L / V) + 8.4
 *
 * Mosher's Formula
 *   SRM = (.3 * W * L / V) + 4.7
 *
 * where W = weight of malt (in lbs.) L = color of malt (in ) V = volume of wort (in gal.)
 *
 * Morey's formula gives a better approximation than the others, since it is a
 * nonlinear conversion. However, if you want simpler math, both Mosher and
 * Daniels' methods are good approximations.
 *
 *
 * http://en.wikipedia.org/wiki/Standard_Reference_Method
 *   SRM = 1.3546 * °L - 0.76
 *   °L = (SRM + 0.76) / 1.3546
 *
 * @author thinner
 */
public class Lovibond extends DoubleUnit {

    public Lovibond(double value) {
        super(value);
    }

    public Lovibond(SRM value) {
        super((value.value() + 0.76) / 1.3546);
    }

	@Override
	public Lovibond deriveNew(double d) {
		return new Lovibond(d);
	}
}

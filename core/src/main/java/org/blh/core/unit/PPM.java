package org.blh.core.unit;

/**
 * Parts per million.
 * 
 * @author thinner
 */
public class PPM extends DoubleUnit {

    public PPM(double value) {
        super(value);
    }

	@Override
	public PPM deriveNew(double d) {
		return new PPM(d);
	}
}

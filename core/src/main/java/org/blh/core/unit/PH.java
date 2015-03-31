package org.blh.core.unit;

/**
 * pH is a measure of the acidity or basicity of an aqueous solution.
 *
 * @author thinner
 */
public class PH extends DoubleUnit {

    public PH(double value) {
        super(value);

        // No magic numbers here
        if (value < 0 || value > 14) {
            throw new IllegalArgumentException("You craaaazzzy. PH is 0-14");
        }
    }

	@Override
	public PH deriveNew(double d) {
		return new PH(d);
	}
}

package org.blh.core.unit;

/**
 * The amount of CO2 to be dissolved in the beer.
 *
 * @author thinner
 */
public class CO2Volumes extends DoubleUnit {

    public CO2Volumes(double value) {
        super(value);
    }

	@Override
	public CO2Volumes deriveNew(double d) {
		return new CO2Volumes(d);
	}
}

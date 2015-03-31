package org.blh.core.unit.volume;

import org.blh.core.unit.DoubleUnit;

/**
 * Retarded rollercoaster unit of volume.
 *
 * @author thinner
 */
public class USGallons extends DoubleUnit {

    public static final double CONVERSION_FACTOR = 0.264172;

    public USGallons(double value) {
        super(value);
    }

    public USGallons(Liters liters) {
        super(liters.value() * CONVERSION_FACTOR);
    }

    public Liters toLiters() {
        return new Liters(this.value() / CONVERSION_FACTOR);
    }

	@Override
	public USGallons deriveNew(double d) {
		return new USGallons(d);
	}
}

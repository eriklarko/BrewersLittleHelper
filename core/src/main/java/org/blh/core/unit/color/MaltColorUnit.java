package org.blh.core.unit.color;

import org.blh.core.unit.DoubleUnit;
import org.blh.core.unit.volume.USGallons;

/**
 * Holds the potential color of the grist, taking into consideration the amount
 * of liquid the color potential is diluted in.
 *
 * @author thinner
 */
public class MaltColorUnit extends DoubleUnit {

    public MaltColorUnit(ColorPotential potential, USGallons finalVolume) {
        super(finalVolume.value() == 0 ? 0 : potential.value() / finalVolume.value());
    }

	private MaltColorUnit(double d) {
		super(d);
	}


	@Override
	public MaltColorUnit deriveNew(double d) {
		return new MaltColorUnit(d);
	}
}

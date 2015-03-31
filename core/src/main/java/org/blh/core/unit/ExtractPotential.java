package org.blh.core.unit;

import org.blh.core.unit.gravity.GravityPoints;
import org.blh.core.unit.weight.Kilograms;

/**
 * Represents how many sugars can potentially be extracted from a grist part.
 *
 * @author thinner
 */
public class ExtractPotential extends DoubleUnit {

    private final GravityPoints gravityPoints;
    private final Kilograms weight;

    public ExtractPotential(GravityPoints gravityPoints) {
        this(gravityPoints, new Kilograms(1));
    }

    public ExtractPotential(GravityPoints gravityPoints, Kilograms weight) {
        super(weight.value() == 0 ? 0 : gravityPoints.value() / weight.value());

        this.gravityPoints = gravityPoints;
        this.weight = weight;
    }

    public GravityPoints getGravityPoints() {
        return gravityPoints;
    }

    public Kilograms getWeight() {
        return weight;
    }

	@Override
	public ExtractPotential deriveNew(double d) {
		if (d == 0) {
			return new ExtractPotential(new GravityPoints(0), new Kilograms(0));
		}

		double scale = super.value() / d;
		GravityPoints newGp = new GravityPoints(gravityPoints.value() * scale);
		Kilograms newWeight = new Kilograms(weight.value() * scale);
		return new ExtractPotential(newGp, newWeight);
	}
}

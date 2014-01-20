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
        super(gravityPoints.value() / weight.value());

        this.gravityPoints = gravityPoints;
        this.weight = weight;
    }

    public GravityPoints getGravityPoints() {
        return gravityPoints;
    }

    public Kilograms getWeight() {
        return weight;
    }
}

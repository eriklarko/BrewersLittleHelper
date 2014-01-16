package org.blh.core.units;

import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class ExtractPotential extends DoubleUnit {

    private final GravityPoints gravityPoints;
    private final Kilograms weight;

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

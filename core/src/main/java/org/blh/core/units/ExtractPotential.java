package org.blh.core.units;

import org.blh.core.units.gravity.GravityPoints;
import org.blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class ExtractPotential extends NumericalUnit {

    private GravityPoints gravityPoints;
    private Kilograms weight;

    public ExtractPotential(GravityPoints gravityPoints, Kilograms weight) {
        super(gravityPoints.value().divide(weight.value()));

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

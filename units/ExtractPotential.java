package units;

import units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class ExtractPotential {

    private int gravityPoints;
    private Kilograms weight;

    public ExtractPotential(int gravityPoints, Kilograms weight) {
        this.gravityPoints = gravityPoints;
        this.weight = weight;
    }

    public double value() {
        return gravityPoints / weight.value();
    }
}

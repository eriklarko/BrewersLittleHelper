package blh.core.units.color;

import blh.core.units.weight.Lbs;

/**
 *
 * @author thinner
 */
public class ColorPotential {

    private double potential = 0;

    public void add(Lovibond color, Lbs amount) {
        potential += color.value() * amount.value();
    }

    public double value() {
        return potential;
    }
}

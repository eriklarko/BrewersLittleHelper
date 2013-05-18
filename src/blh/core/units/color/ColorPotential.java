package blh.core.units.color;

import blh.core.units.Unit;
import blh.core.units.weight.Lbs;

/**
 *
 * @author thinner
 */
public class ColorPotential extends Unit<Double> {

    public ColorPotential() {
        super(0d);
    }

    public void add(Lovibond color, Lbs amount) {
        value += color.value() * amount.value();
    }
}

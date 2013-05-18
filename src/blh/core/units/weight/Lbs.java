package blh.core.units.weight;

import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Lbs extends Unit<Double> {

    public Lbs(Kilograms kgs) {
        super(kgs.value() * 2.20462);
    }
}
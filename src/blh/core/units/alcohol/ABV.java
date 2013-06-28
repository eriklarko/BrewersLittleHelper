package blh.core.units.alcohol;

import blh.core.units.Factor;
import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class ABV extends Unit<Factor> {

    public ABV(Factor value) {
        super(value);
    }

    public ABV(double value) {
        super(new Factor(value));
    }
}

package blh.core.units.color;

import blh.core.units.Unit;

/**
 * EBC = 1.97 * SRM -> SRM = EBC / 1.97
 *
 * @author thinner
 */
public class SRM extends Unit<Double> {

    public static final double EBC_CONVERSION_FACTOR = 1.97;

    public SRM(double value) {
        super(value);
    }

    public SRM(EBC ebc) {
        super(ebc.value() / 1.97);
    }

    public EBC toEBC() {
        return new EBC(this.value() * EBC_CONVERSION_FACTOR);
    }
}

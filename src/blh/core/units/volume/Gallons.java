package blh.core.units.volume;

import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Gallons extends Unit<Double>{
    public static final double CONVERSION_FACTOR = 0.264172;

    public Gallons(double value) {
        super(value);
    }

    public Gallons(Liters liters) {
        super(liters.value() * CONVERSION_FACTOR);
    }

    public Liters toLiters() {
        return new Liters(this.value() / CONVERSION_FACTOR);
    }
}

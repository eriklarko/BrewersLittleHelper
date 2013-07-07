package blh.core.units.volume;

import blh.core.units.Unit;

/**
 * Created by Erik Larkö at 7/4/13 11:00 PM
 */
public class Milliliters extends Unit<Double> {

    public Milliliters(double value) {
        super(value);
    }

    public Milliliters(Liters liters) {
        super(liters.value() * 1000);
    }
}

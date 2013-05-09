package blh.core.units.volume;

import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Gallons extends Unit<Double>{

    public Gallons(Liters liters) {
        super(liters.value() * 0.264172);
    }
}

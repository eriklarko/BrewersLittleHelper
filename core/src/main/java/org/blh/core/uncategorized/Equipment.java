package org.blh.core.uncategorized;

import org.blh.core.units.Fraction;
import org.blh.core.units.Percentage;
import org.blh.core.units.time.Hour;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class Equipment {

    private Fraction<Liters, Hour> boilOff;
    private Percentage coolingLoss;
    private Liters trubLoss;

    public Fraction<Liters, Hour> getBoilOff() {
        return boilOff;
    }

    public Percentage getCoolingLoss() {
        return coolingLoss;
    }

    public Liters getTrubLoss() {
        return trubLoss;
    }
}

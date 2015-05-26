package org.blh.context;

import org.blh.core.unit.Fraction;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Hour;
import org.blh.core.unit.volume.Liters;

/**
 * Defines the properties of the equipment used when brewing.
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

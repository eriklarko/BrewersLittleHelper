package org.blh.core.units.distance;

import org.blh.core.units.Unit;

/**
 * 1 meter = 39.3700787 inches
 * 
 * Created by Erik Larkö at 6/23/13 4:32 PM
 */
public class Inch extends Unit<Double> {

    public static final double CONVERSION_FACTOR = 39.3700787;

    public Inch(double value) {
        super(value);
    }
    
    public Inch(Meters value) {
        super(value.value() * CONVERSION_FACTOR);
    }
    
    public Meters toMeters() {
        return new Meters(this.value / CONVERSION_FACTOR);
    }
}

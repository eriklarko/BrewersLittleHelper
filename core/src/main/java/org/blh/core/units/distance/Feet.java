package org.blh.core.units.distance;

import org.blh.core.units.Unit;

/**
 * 1 meter = 3.2808399 feet
 * 
 * Created by Erik Larkö at 6/23/13 4:28 PM
 */
public class Feet extends Unit<Double> {
    
    public static final double CONVERSION_FACTOR = 3.2808399;

    public Feet(double value) {
        super(value);
    }
    
    public Feet(Meters value) {
        super(value.value() * CONVERSION_FACTOR);
    }
    
    public Meters toMeters() {
        return new Meters(this.value / CONVERSION_FACTOR);
    }
}

package org.blh.core.units.weight;

import org.blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Grams extends Unit<Double> {

    public Grams(double value) {
        super(value);
    }
    
    public Grams(Kilograms kg) {
        super(kg.value() * 1000);
    }
    
    public Kilograms toKilograms() {
        return new Kilograms(this.value / 1000);
    }
}

package blh.core.units.weight;

import blh.core.units.Unit;

/**
 *
 * @author thinner
 */
public class Kilograms extends Unit<Double> {

    public Kilograms(double value) {
        super(value);
    }
    
    public Grams inGrams() {
        return new Grams(this.value() / 1000);
    }
}

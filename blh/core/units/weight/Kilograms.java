package blh.core.units.weight;

/**
 *
 * @author thinner
 */
public class Kilograms {

    private double value;

    public Kilograms(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
    
    public Grams inGrams() {
        return new Grams(this.value / 1000);
    }
}

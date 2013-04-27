package blh.core.units.weight;

/**
 *
 * @author thinner
 */
public class Lbs {
    private double value;

    public Lbs(Kilograms kgs) {
        this.value = kgs.value() * 2.20462;
    }

    public double value() {
        return value;
    }
}

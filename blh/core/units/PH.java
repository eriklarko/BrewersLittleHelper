package blh.core.units;

/**
 *
 * @author thinner
 */
public class PH {

    private double value;

    public PH(double value) {
        if (value < 0 || value > 14) {
            throw new IllegalArgumentException("You craaaazzzy. PH is 0-14");
        }
        this.value = value;
    }

    public double value() {
        return value;
    }
}

package blh.core.units;

/**
 * On the form xy.zw%. For 0.xyzw, use Factor.
 * @author thinner
 */
public class Percentage {

    private double value;

    public Percentage(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}

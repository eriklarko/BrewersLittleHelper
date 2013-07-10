package blh.core.units;

/**
 * On the form xy.zw%. For 0.xyzw, use Factor.
 *
 * @author thinner
 */
public class Percentage extends Unit<Double> {

    public Percentage(double value) {
        super(value);
    }

    public Percentage(Factor value) {
        super(value.value() * 100);
    }

    public Factor asFactor() {
        return new Factor(this.value / 100);
    }
}

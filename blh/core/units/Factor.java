package blh.core.units;

/**
 * On the form 0.xyzw. For xy.zw% use Percentage.
 * @author thinner
 */
public class Factor {

    private double value;

    public Factor(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}

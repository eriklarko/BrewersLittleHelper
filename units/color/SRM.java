package units.color;

/**
 * EBC = 1.97 * SRM -> SRM = EBC / 1.97
 * @author thinner
 */
public class SRM {

    private double value;

    public SRM(double value) {
        this.value = value;
    }
    
    public double value() {
        return value;
    }
}

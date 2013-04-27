package units.volume;

/**
 *
 * @author thinner
 */
public class Gallons {

    private double value;

    public Gallons(Liters liters) {
        this.value = liters.value() * 0.264172;
    }

    public double value() {
        return value;
    }
}

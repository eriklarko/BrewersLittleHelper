package blh.core.units;

/**
 *
 * @author thinner
 */
public class BJCPTasteRating extends Unit<Double> {

    public BJCPTasteRating(double value) {
        super(value);
        if (value < 0 || value > 50) {
            throw new IllegalArgumentException("The BJCP taste rating is a number between 0 and 50, you said " + value);
        }
    }
}

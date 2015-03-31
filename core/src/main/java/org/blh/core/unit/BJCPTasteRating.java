package org.blh.core.unit;

/**
 * The rating a brew got in a BJCP competition.
 *
 * @author thinner
 */
public class BJCPTasteRating extends DoubleUnit {

    public static final int MAX_RATING = 50;

    public BJCPTasteRating(double value) {
        super(value);
        if (value < 0 || value > MAX_RATING) {
            throw new IllegalArgumentException("The BJCP taste rating is a number between 0 and " + MAX_RATING + ", you said " + value);
        }
    }

	@Override
	public BJCPTasteRating deriveNew(double d) {
		return new BJCPTasteRating(d);
	}
}

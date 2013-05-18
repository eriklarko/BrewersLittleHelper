package blh.core.recipe;

import blh.core.ingredients.Malt;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class GristPart {

    private Malt malt;
    private Kilograms amount;

    public GristPart(Malt malt, Kilograms amount) {
        this.malt = malt;
        this.amount = amount;
    }

    public Malt getMalt() {
        return malt;
    }

    public Kilograms getAmount() {
        return amount;
    }
}

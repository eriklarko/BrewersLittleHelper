package org.blh.core.recipe;

import org.blh.core.ingredient.Malt;
import org.blh.core.unit.weight.Kilograms;

/**
 * Represents part of the grist, i.e. a malt addition.
 *
 * @author thinner
 */
public class GristPart {

    private final Malt malt;
    private final Kilograms amount;

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

package org.blh.core.recipe;

import org.blh.core.ingredients.Hop;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.weight.Grams;

/**
 *
 * @author thinner
 */
public class HopAddition {

    private final Hop hop;
    private final Minutes timeInBoil;
    private final Grams amount;

    public HopAddition(Hop hop, Minutes timeInBoil, Grams amount) {
        this.hop = hop;
        this.timeInBoil = timeInBoil;
        this.amount = amount;
    }

    public Hop getHop() {
        return hop;
    }

    public Minutes getTimeInBoil() {
        return timeInBoil;
    }

    public Grams getAmount() {
        return amount;
    }
}

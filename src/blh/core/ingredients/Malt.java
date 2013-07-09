package blh.core.ingredients;

import blh.core.units.ExtractPotential;
import blh.core.units.color.Lovibond;

/**
 *
 * @author thinner
 */
public class Malt {

    public static enum TYPE {
        GRAIN, EXTRACT, SUGAR
    }

    public final String name;
    public final Lovibond color;
    public final ExtractPotential extractPotential;
    public final TYPE type;

    public Malt(String name, Lovibond color, ExtractPotential extractPotential, TYPE type) {
        this.name = name;
        this.color = color;
        this.extractPotential = extractPotential;
        this.type = type;
    }
}

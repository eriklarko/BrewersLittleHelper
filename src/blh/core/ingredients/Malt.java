package blh.core.ingredients;

import blh.core.units.color.Lovibond;
import blh.core.units.gravity.GravityPoints;

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
    public final GravityPoints extractPotential;
    public final TYPE type;

    public Malt(String name, Lovibond color, GravityPoints extractPotential, TYPE type) {
        this.name = name;
        this.color = color;
        this.extractPotential = extractPotential;
        this.type = type;
    }
}

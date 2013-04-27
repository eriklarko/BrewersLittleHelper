package ingredients;

import units.color.Lovibond;

/**
 *
 * @author thinner
 */
public class Malt {

    public final String name;
    public final Lovibond color;
    public final int extractPotential;

    public Malt(String name, Lovibond color, int extractPotential) {
        this.name = name;
        this.color = color;
        this.extractPotential = extractPotential;
    }
}

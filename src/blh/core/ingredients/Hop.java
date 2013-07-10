package blh.core.ingredients;

import blh.core.units.Percentage;

/**
 *
 * @author thinner
 */
public class Hop {
    public final String name;
    public final Percentage alphaAcids;
    
    public Hop(String name, Percentage alphaAcids) {
        this.name = name;
        this.alphaAcids = alphaAcids;
    }
}

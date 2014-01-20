package org.blh.core.ingredient;

import org.blh.core.unit.Percentage;

/**
 * Represents a hop variety.
 *
 * @author thinner
 */
public class Hop {

    private final String name;
    private final Percentage alphaAcids;

    public Hop(String name, Percentage alphaAcids) {
        this.name = name;
        this.alphaAcids = alphaAcids;
    }

    public String getName() {
        return name;
    }

    public Percentage getAlphaAcids() {
        return alphaAcids;
    }
}

package org.blh.core.ingredients;

import org.blh.core.units.Percentage;

/**
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

package org.blh.core.ingredients;

import org.blh.core.units.ExtractPotential;
import org.blh.core.units.color.Lovibond;

/**
 *
 * @author thinner
 */
public class Malt {

    public static enum TYPE {
        GRAIN, EXTRACT, SUGAR
    }

    private final String name;
    private final Lovibond color;
    private final ExtractPotential extractPotential;
    private final TYPE type;

    public Malt(String name, Lovibond color, ExtractPotential extractPotential, TYPE type) {
        this.name = name;
        this.color = color;
        this.extractPotential = extractPotential;
        this.type = type;
    }

	public String getName() {
		return name;
	}

	public Lovibond getColor() {
		return color;
	}

	public ExtractPotential getExtractPotential() {
		return extractPotential;
	}

	public TYPE getType() {
		return type;
	}
}

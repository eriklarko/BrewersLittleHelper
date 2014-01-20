package org.blh.core.ingredient;

import org.blh.core.unit.ExtractPotential;
import org.blh.core.unit.color.Lovibond;

/**
 * Represents a malt type.
 *
 * @author thinner
 */
public class Malt {

    /**
     * Describes the very broad malt type. The values in the enum should give
     * you an idea of what it's describing.
     */
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

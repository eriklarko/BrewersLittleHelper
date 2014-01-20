package org.blh.recipe.uncategorized;

import org.blh.core.uncategorized.BeerType;

/**
 * Contains information about a recipe that is not really part of the steps
 * needed to reproduce it.
 *
 * Created by Erik Larkö at 7/4/13 11:02 PM
 */
public class RecipeMetaData {

    private final BeerType type;
    private final String name;

    public RecipeMetaData(BeerType type, String name) {
        this.type = type;
        this.name = name;
    }

    public BeerType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

package org.blh.core.uncategorized;

/**
 * Created by Erik Larkö at 7/4/13 11:02 PM
 */
public class RecipeMetaData {

    public static enum BEER_TYPE {
        ALE, LAGER, HYBRID
    }

    private final BEER_TYPE type;
	private final String name;

	public RecipeMetaData(BEER_TYPE type, String name) {
		this.type = type;
		this.name = name;
	}

	public BEER_TYPE getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}

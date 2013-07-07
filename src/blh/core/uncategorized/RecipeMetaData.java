package blh.core.uncategorized;

/**
 * Created by Erik Larkö at 7/4/13 11:02 PM
 */
public class RecipeMetaData {

    public static enum BEER_TYPE {
        ALE, LAGER, HYBRID
    }

    public final BEER_TYPE type;

    public RecipeMetaData(BEER_TYPE type) {
        this.type = type;
    }
}

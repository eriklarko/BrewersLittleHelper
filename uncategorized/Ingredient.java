package uncategorized;


/**
 *
 * @author thinner
 */
public interface Ingredient {

    enum Type {

        HOP, MALT, YEAST
    }

    String getName();

    Type getType();
}

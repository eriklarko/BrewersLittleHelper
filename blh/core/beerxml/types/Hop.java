package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class Hop implements BeerXMLRecord {

    public final String name;
    public final Percentage alpha;
    public final Kilograms amount;
    public final USE use;
    public final Minutes time;
    public final String notes;
    public final TYPE type;
    public final FORM form;
    public final Percentage beta;
    public final Percentage hopStabilityIndex;
    public final String origin;
    public final String substitutes;
    public final Percentage humulene;
    public final Percentage caryophyllene;
    public final Percentage cohumulone;
    public final Percentage myrcene;

    public static enum USE {

        BOIL, DRY_HOP,
        MASH, FIRST_WORT,
        AROMA
    }

    public static enum TYPE {

        BITTERING, AROMA, BOTH
    }

    public static enum FORM {

        LEAF, PELLET, PLUG
    }

    public Hop(String name, Percentage alpha, Kilograms amount, USE use,
            Minutes time, String notes, TYPE type, FORM form, Percentage beta,
            Percentage hopStabilityIndex, String origin, String substitutes,
            Percentage humulene, Percentage caryophyllene,
            Percentage cohumulone, Percentage myrcene) {
        this.name = name;
        this.alpha = alpha;
        this.amount = amount;
        this.use = use;
        this.time = time;
        this.notes = notes;
        this.type = type;
        this.form = form;
        this.beta = beta;
        this.hopStabilityIndex = hopStabilityIndex;
        this.origin = origin;
        this.substitutes = substitutes;
        this.humulene = humulene;
        this.caryophyllene = caryophyllene;
        this.cohumulone = cohumulone;
        this.myrcene = myrcene;
    }
}

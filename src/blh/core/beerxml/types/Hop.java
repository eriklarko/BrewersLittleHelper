package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class Hop implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String ALPHA = "ALPHA";
    public static final String AMOUNT = "AMOUNT";
    public static final String USE = "USE";
    public static final String TIME = "TIME";
    public static final String NOTES = "NOTES";
    public static final String TYPE = "TYPE";
    public static final String FORM = "FORM";
    public static final String BETA = "BETA";
    public static final String HOP_STABILITY_INDEX = "HSI";
    public static final String ORIGIN = "ORIGIN";
    public static final String SUBSTITUTES = "SUBSTITUTES";
    public static final String HUMULENE = "HUMULENE";
    public static final String CARYOPHYLLENE = "CARYOPHYLLENE";
    public static final String COHUMULONE = "COHUMULONE";
    public static final String MYRCENE = "MYRCENE";
    
    public final String name;
    public final Percentage alpha;
    public final Kilograms amount;
    public final HOP_USE use;
    public final Minutes time;
    public final String notes;
    public final HOP_TYPE type;
    public final HOP_FORM form;
    public final Percentage beta;
    public final Percentage hopStabilityIndex;
    public final String origin;
    public final String substitutes;
    public final Percentage humulene;
    public final Percentage caryophyllene;
    public final Percentage cohumulone;
    public final Percentage myrcene;

    public static enum HOP_USE {

        BOIL, DRY_HOP,
        MASH, FIRST_WORT,
        AROMA
    }

    public static enum HOP_TYPE {

        BITTERING, AROMA, BOTH
    }

    public static enum HOP_FORM {

        LEAF, PELLET, PLUG
    }

    public Hop(String name, Percentage alpha, Kilograms amount, HOP_USE use,
            Minutes time, String notes, HOP_TYPE type, HOP_FORM form, Percentage beta,
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

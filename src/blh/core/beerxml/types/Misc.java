package blh.core.beerxml.types;

import blh.core.units.time.Minutes;

/**
 *
 * @author thinner
 */
public class Misc implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String USE = "USE";
    public static final String TIME = "TIME";
    public static final String AMOUNT = "AMOUNT";
    public static final String AMOUNT_IS_WEIGHT = "AMOUNT_IS_WEIGHT";
    public static final String USE_FOR = "USE_FOR";
    public static final String NOTES = "NOTES";
    
    public final String name;
    public final MISC_TYPE type;
    public final MISC_USE use;
    public final Minutes time;
    public final double amount;
    public final boolean amountIsWeight;
    public final String useFor;
    public final String notes;

    public static enum MISC_TYPE {

        SPICE, FINING, WATER, AGENT, HERB, FLAVOR, OTHER
    }

    public static enum MISC_USE {

        BOIL, MASH, PRIMARY, SECONDARY, BOTTLING
    }

    public Misc(String name, MISC_TYPE type, MISC_USE use, Minutes time, double amount, boolean amountIsWeight, String useFor, String notes) {
        this.name = name;
        this.type = type;
        this.use = use;
        this.time = time;
        this.amount = amount;
        this.amountIsWeight = amountIsWeight;
        this.useFor = useFor;
        this.notes = notes;
    }
}

package blh.core.beerxml.types;

import blh.core.units.time.Minutes;

/**
 *
 * @author thinner
 */
public class Misc {

    public final String name;
    public final TYPE type;
    public final USE use;
    public final Minutes time;
    public final double amount;
    public final boolean amountIsWeight;
    public final String useFor;
    public final String notes;

    public static enum TYPE {

        SPICE, FINING, WATER, AGENT, HERB, FLAVOR, OTHER
    }

    public static enum USE {

        BOIL, MASH, PRIMARY, SECONDARY, BOTTLING
    }

    public Misc(String name, TYPE type, USE use, Minutes time, double amount, boolean amountIsWeight, String useFor, String notes) {
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

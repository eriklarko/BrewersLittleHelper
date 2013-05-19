package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.color.Lovibond;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class Fermentable implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String AMOUNT = "AMOUNT";
    public static final String YIELD = "YIELD";
    public static final String COLOR = "COLOR";
    public static final String ADD_AFTER_BOIL = "ADD_AFTER_BOIL";
    public static final String ORIGIN = "ORIGIN";
    public static final String SUPPLIER = "SUPPLIER";
    public static final String NOTES = "NOTES";
    public static final String MAX_IN_BATCH = "MAX_IN_BATCH";
    
    public final String name;
    public final FERMENTABLE_TYPE type;
    public final Kilograms amount;
    public final Percentage yield;
    /**
     * Is SRM for liquid extracts
     */
    public final Lovibond color;
    public final boolean addAfterBoil;
    public final String origin;
    public final String supplier;
    public final String notes;
    public final Percentage maxInBatch;

    /**
     * Note that according to the BeerXML standard, EXTRACT is liquid extract
     */
    public static enum FERMENTABLE_TYPE {

        GRAIN, SUGAR, EXTRACT, DRY_EXTRACT, ADJUNCT
    }

    public Fermentable(String name, FERMENTABLE_TYPE type, Kilograms amount,
            Percentage yield, Lovibond color, boolean addAfterBoil,
            String origin, String supplier, String notes, Percentage maxInBatch) {

        if (type.equals(FERMENTABLE_TYPE.ADJUNCT) || type.equals(FERMENTABLE_TYPE.GRAIN) || type.equals(FERMENTABLE_TYPE.EXTRACT)) {
            throw new IllegalArgumentException("Please use proper class for extracts, adjuncts and grains");
        }

        this.name = name;
        this.type = type;
        this.amount = amount;
        this.yield = yield;
        this.color = color;
        this.addAfterBoil = addAfterBoil;
        this.origin = origin;
        this.supplier = supplier;
        this.notes = notes;
        this.maxInBatch = maxInBatch;
    }

    protected Fermentable(int a, String name, FERMENTABLE_TYPE type, Kilograms amount,
            Percentage yield, Lovibond color, boolean addAfterBoil,
            String origin, String supplier, String notes, Percentage maxInBatch) {

        this.name = name;
        this.type = type;
        this.amount = amount;
        this.yield = yield;
        this.color = color;
        this.addAfterBoil = addAfterBoil;
        this.origin = origin;
        this.supplier = supplier;
        this.notes = notes;
        this.maxInBatch = maxInBatch;
    }
}

package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;

/**
 *
 * @author thinner
 */
public class Yeast implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String FORM = "FORM";
    public static final String AMOUNT = "AMOUNT";
    public static final String AMOUNT_IS_WEIGHT = "AMOUNT_IS_WEIGHT";
    public static final String LABORATORY = "LABORATORY";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String MIN_TEMPERATURE = "MIN_TEMPERATURE";
    public static final String MAX_TEMPERATURE = "MAX_TEMPERATURE";
    public static final String FLOCCULATION = "FLOCCULATION";
    public static final String ATTENUATION = "ATTENUATION";
    public static final String NOTES = "NOTES";
    public static final String BEST_FOR = "BEST_FOR";
    public static final String TIMES_CULTURED = "TIMES_CULTURED";
    public static final String MAX_REUSE = "MAX_REUSE";
    public static final String ADD_TO_SECONDARY = "ADD_TO_SECONDARY";
    
    public final String name;
    public final YEAST_TYPE type;
    public final YEAST_FORM form;
    /**
     * Is kilograms if AMOUNT_IS_WEIGHT is true, liters otherwise
     */
    public final double amount;
    public final boolean amountIsWeight;
    public final String laboratory;
    public final String productId;
    public final Celcius minTemperature;
    public final Celcius maxTemperature;
    public final YEAST_FLOCCULATION flocculation;
    public final Percentage attenuation;
    public final String notes;
    public final String bestFor;
    public final int timesCultured;
    public final int maxReuse;
    public final boolean addToSecondary;

    public static enum YEAST_TYPE {

        ALE, LAGER, WHEAT, WINE, CHAMPAGNE
    }

    public static enum YEAST_FORM {

        LIQUID, DRY, SLANT, CULTURE
    }

    public static enum YEAST_FLOCCULATION {

        LOW, MEDIUM, HIGH, VERY_HIGH
    }

    public Yeast(String name, YEAST_TYPE type, YEAST_FORM form, double amount, 
            boolean amountIsWeight, String laboratory, String productId, 
            Celcius minTemperature, Celcius maxTemperature, 
            YEAST_FLOCCULATION flocculation, Percentage attenuation, String notes, 
            String bestFor, int timesCultured, int maxReuse, boolean addToSecondary) {
        this.name = name;
        this.type = type;
        this.form = form;
        this.amount = amount;
        this.amountIsWeight = amountIsWeight;
        this.laboratory = laboratory;
        this.productId = productId;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.flocculation = flocculation;
        this.attenuation = attenuation;
        this.notes = notes;
        this.bestFor = bestFor;
        this.timesCultured = timesCultured;
        this.maxReuse = maxReuse;
        this.addToSecondary = addToSecondary;
    }
}

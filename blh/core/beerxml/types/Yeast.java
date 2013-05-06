package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;

/**
 *
 * @author thinner
 */
public class Yeast {

    public final String name;
    public final TYPE type;
    public final FORM form;
    /**
     * Is kilograms if AMOUNT_IS_WEIGHT is true, liters otherwise
     */
    public final double amount;
    public final boolean amountIsWeight;
    public final String laboratory;
    public final String productId;
    public final Celcius minTemperature;
    public final Celcius maxTemperature;
    public final FLOCCULATION flocculation;
    public final Percentage attenuation;
    public final String notes;
    public final String bestFor;
    public final int timesCultured;
    public final int maxReuse;
    public final boolean addToSecondary;

    public static enum TYPE {

        ALE, LAGER, WHEAT, WINE, CHAMPAGNE
    }

    public static enum FORM {

        LIQUID, DRY, SLANT, CULTURE
    }

    public static enum FLOCCULATION {

        LOW, MEDIUM, HIGH, VERY_HIGH
    }

    public Yeast(String name, TYPE type, FORM form, double amount, 
            boolean amountIsWeight, String laboratory, String productId, 
            Celcius minTemperature, Celcius maxTemperature, 
            FLOCCULATION flocculation, Percentage attenuation, String notes, 
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

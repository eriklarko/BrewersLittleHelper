package blh.core.beerxml.types;

import blh.core.units.BJCPTasteRating;
import blh.core.units.CO2Volumes;
import blh.core.units.Factor;
import blh.core.units.Percentage;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.temperature.Celcius;
import blh.core.units.time.Days;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thinner
 */
public class Recipe implements BeerXMLRecord {

    public final String name;
    public final TYPE type;
    public final Style style;
    public final Equipment equipment;
    public final String brewer;
    public final String assistantBrewer;
    public final Liters batchSize;
    public final Liters boilSize;
    public final Minutes boilTime;
    /**
     * Is 100% for extract types;
     */
    public final Percentage efficiency;
    public final List<Hop> hops;
    public final List<Fermentable> fermentables;
    public final List<Misc> miscs;
    public final List<Yeast> yeasts;
    public final List<Water> waters;
    public final MashProfile mashProfile;
    public final String notes;
    public final String tasteNotes;
    public final BJCPTasteRating tasteRating;
    public final SpecificGravity measuredOriginalGravity;
    public final SpecificGravity measuredFinalGravity;
    /**
     * Typically between 1 and 3
     */
    public final int fermentationStages;
    public final Days primaryAge;
    public final Celcius primaryTemperature;
    public final Days secondaryAge;
    public final Celcius secondaryTemperature;
    public final Days tertiaryAge;
    public final Celcius tertiaryTemperature;
    public final Days ageAfterBottling;
    public final Celcius temperatureDuringAfterBottlingAge;
    public final Date date;
    public final CO2Volumes carbonation;
    public final boolean forcedCarbonation;
    /**
     * Only used if forcedCarbonation is false.
     */
    public final String primingSugarName;
    public final Celcius carbonationTemperature;
    public final Factor primingSugarEquivalence;
    public final Factor kegPrimingFactor;

    public static enum TYPE {

        EXTRACT, ALL_GRAIN, PARTIAL_MASH
    }

    public Recipe(String name, TYPE type, Style style, Equipment equipment, String brewer, String assistantBrewer, Liters batchSize, Liters boilSize, Minutes boilTime, Percentage efficiency, List<Hop> hops, List<Fermentable> fermentables, List<Misc> miscs, List<Yeast> yeasts, List<Water> waters, MashProfile mashProfile, String notes, String tasteNotes, BJCPTasteRating tasteRating, SpecificGravity measuredOriginalGravity, SpecificGravity measuredFinalGravity, int fermentationStages, Days primaryAge, Celcius primaryTemperature, Days secondaryAge, Celcius secondaryTemperature, Days tertiaryAge, Celcius tertiaryTemperature, Days ageAfterBottling, Celcius temperatureDuringAfterBottlingAge, Date date, CO2Volumes carbonation, boolean forcedCarbonation, String primingSugarName, Celcius carbonationTemperature, Factor primingSugarEquivalence, Factor kegPrimingFactor) {
        this.name = name;
        this.type = type;
        this.style = style;
        this.equipment = equipment;
        this.brewer = brewer;
        this.assistantBrewer = assistantBrewer;
        this.batchSize = batchSize;
        this.boilSize = boilSize;
        this.boilTime = boilTime;
        this.efficiency = efficiency;
        this.hops = hops;
        this.fermentables = fermentables;
        this.miscs = miscs;
        this.yeasts = yeasts;
        this.waters = waters;
        this.mashProfile = mashProfile;
        this.notes = notes;
        this.tasteNotes = tasteNotes;
        this.tasteRating = tasteRating;
        this.measuredOriginalGravity = measuredOriginalGravity;
        this.measuredFinalGravity = measuredFinalGravity;
        this.fermentationStages = fermentationStages;
        this.primaryAge = primaryAge;
        this.primaryTemperature = primaryTemperature;
        this.secondaryAge = secondaryAge;
        this.secondaryTemperature = secondaryTemperature;
        this.tertiaryAge = tertiaryAge;
        this.tertiaryTemperature = tertiaryTemperature;
        this.ageAfterBottling = ageAfterBottling;
        this.temperatureDuringAfterBottlingAge = temperatureDuringAfterBottlingAge;
        this.date = date;
        this.carbonation = carbonation;
        this.forcedCarbonation = forcedCarbonation;
        this.primingSugarName = primingSugarName;
        this.carbonationTemperature = carbonationTemperature;
        this.primingSugarEquivalence = primingSugarEquivalence;
        this.kegPrimingFactor = kegPrimingFactor;
    }
}

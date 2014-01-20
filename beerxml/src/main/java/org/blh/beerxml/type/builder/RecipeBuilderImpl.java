package org.blh.beerxml.type.builder;

import java.text.DateFormat;
import java.util.List;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.Utils;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.Equipment;
import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Recipe;
import org.blh.beerxml.type.Recipe.TYPE;
import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Water;
import org.blh.beerxml.type.Yeast;
import org.blh.core.unit.BJCPTasteRating;
import org.blh.core.unit.CO2Volumes;
import org.blh.core.unit.Factor;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.time.Days;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.joda.time.DateTime;

/**
 * Builder for the Recipe type.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class RecipeBuilderImpl implements RecipeBuilder {

    private String name;
    private TYPE type;
    private Style style;
    private Equipment equipment;
    private String brewer;
    private String assistantBrewer;
    private Liters batchSize;
    private Liters boilSize;
    private Minutes boilTime;
    private Percentage efficiency;
    private List<Hop> hops;
    private List<Fermentable> fermentables;
    private List<Misc> miscs;
    private List<Yeast> yeasts;
    private List<Water> waters;
    private MashProfile mashProfile;
    private String notes;
    private String tasteNotes;
    private BJCPTasteRating tasteRating;
    private SpecificGravity measuredOriginalGravity;
    private SpecificGravity measuredFinalGravity;
    private int fermentationStages;
    private Days primaryAge;
    private Celsius primaryTemperature;
    private Days secondaryAge;
    private Celsius secondaryTemperature;
    private Days tertiaryAge;
    private Celsius tertiaryTemperature;
    private Days ageAfterBottling;
    private Celsius temperatureDuringAfterBottlingAge;
    private DateTime date;
    private CO2Volumes carbonation;
    private boolean forcedCarbonation;
    private String primingSugarName;
    private Celsius carbonationTemperature;
    private Factor primingSugarEquivalence;
    private Factor kegPrimingFactor;
    private final DateFormat dateFormat;
    private String carbonationUsed;

    public RecipeBuilderImpl() {
        this.dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    }

    @Override
    public RecipeBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public RecipeBuilderImpl setType(TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public RecipeBuilderImpl setStyle(Style style) {
        this.style = style;
        return this;
    }

    @Override
    public RecipeBuilderImpl setEquipment(Equipment equipment) {
        this.equipment = equipment;
        return this;
    }

    @Override
    public RecipeBuilderImpl setBrewer(String brewer) {
        this.brewer = brewer;
        return this;
    }

    @Override
    public RecipeBuilderImpl setAssistantBrewer(String assistantBrewer) {
        this.assistantBrewer = assistantBrewer;
        return this;
    }

    @Override
    public RecipeBuilderImpl setBatchSize(Liters batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    @Override
    public RecipeBuilderImpl setBoilSize(Liters boilSize) {
        this.boilSize = boilSize;
        return this;
    }

    @Override
    public RecipeBuilderImpl setBoilTime(Minutes boilTime) {
        this.boilTime = boilTime;
        return this;
    }

    @Override
    public RecipeBuilderImpl setEfficiency(Percentage efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    @Override
    public RecipeBuilderImpl setHops(List<Hop> hops) {
        this.hops = hops;
        return this;
    }

    @Override
    public RecipeBuilderImpl setFermentables(List<Fermentable> fermentables) {
        this.fermentables = fermentables;
        return this;
    }

    @Override
    public RecipeBuilderImpl setMiscs(List<Misc> miscs) {
        this.miscs = miscs;
        return this;
    }

    @Override
    public RecipeBuilderImpl setYeasts(List<Yeast> yeasts) {
        this.yeasts = yeasts;
        return this;
    }

    @Override
    public RecipeBuilderImpl setWaters(List<Water> waters) {
        this.waters = waters;
        return this;
    }

    @Override
    public RecipeBuilderImpl setMashProfile(MashProfile mashProfile) {
        this.mashProfile = mashProfile;
        return this;
    }

    @Override
    public RecipeBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTasteNotes(String tasteNotes) {
        this.tasteNotes = tasteNotes;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTasteRating(BJCPTasteRating tasteRating) {
        this.tasteRating = tasteRating;
        return this;
    }

    @Override
    public RecipeBuilderImpl setMeasuredOriginalGravity(SpecificGravity measuredOriginalGravity) {
        this.measuredOriginalGravity = measuredOriginalGravity;
        return this;
    }

    @Override
    public RecipeBuilderImpl setMeasuredFinalGravity(SpecificGravity measuredFinalGravity) {
        this.measuredFinalGravity = measuredFinalGravity;
        return this;
    }

    @Override
    public RecipeBuilderImpl setFermentationStages(int fermentationStages) {
        this.fermentationStages = fermentationStages;
        return this;
    }

    @Override
    public RecipeBuilderImpl setPrimaryAge(Days primaryAge) {
        this.primaryAge = primaryAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setPrimaryTemperature(Celsius primaryTemperature) {
        this.primaryTemperature = primaryTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setSecondaryAge(Days secondaryAge) {
        this.secondaryAge = secondaryAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setSecondaryTemperature(Celsius secondaryTemperature) {
        this.secondaryTemperature = secondaryTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTertiaryAge(Days tertiaryAge) {
        this.tertiaryAge = tertiaryAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTertiaryTemperature(Celsius tertiaryTemperature) {
        this.tertiaryTemperature = tertiaryTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setAgeAfterBottling(Days ageAfterBottling) {
        this.ageAfterBottling = ageAfterBottling;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTemperatureDuringAfterBottlingAge(Celsius temperatureDuringAfterBottlingAge) {
        this.temperatureDuringAfterBottlingAge = temperatureDuringAfterBottlingAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setDate(DateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public RecipeBuilderImpl setCarbonation(CO2Volumes carbonation) {
        this.carbonation = carbonation;
        return this;
    }

    @Override
    public RecipeBuilderImpl setForcedCarbonation(boolean forcedCarbonation) {
        this.forcedCarbonation = forcedCarbonation;
        return this;
    }

    @Override
    public RecipeBuilderImpl setPrimingSugarName(String primingSugarName) {
        this.primingSugarName = primingSugarName;
        return this;
    }

    @Override
    public RecipeBuilderImpl setCarbonationTemperature(Celsius carbonationTemperature) {
        this.carbonationTemperature = carbonationTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setPrimingSugarEquivalence(Factor primingSugarEquivalence) {
        this.primingSugarEquivalence = primingSugarEquivalence;
        return this;
    }

    @Override
    public RecipeBuilderImpl setKegPrimingFactor(Factor kegPrimingFactor) {
        this.kegPrimingFactor = kegPrimingFactor;
        return this;
    }

    @Override
    public RecipeBuilder setCarbonationUsed(String carbonationUsed) {
        this.carbonationUsed = carbonationUsed;
        return this;
    }

    // This is as complex as it needs to be
    @Override
    public Builder<Recipe> set(String tagName, String value) throws ParseException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION:
                break;
            case Recipe.NAME:
                name = value;
                break;
            case Recipe.TYPE:
                type = TYPE.valueOf(value.replace(" ", "_").toUpperCase());
                break;
            case Recipe.BREWER:
                brewer = value;
                break;
            case Recipe.ASSISTANT_BREWER:
                assistantBrewer = value;
                break;
            case Recipe.BATCH_SIZE:
                batchSize = new Liters(Double.parseDouble(value));
                break;
            case Recipe.BOIL_SIZE:
                boilSize = new Liters(Double.parseDouble(value));
                break;
            case Recipe.BOIL_TIME:
                boilTime = new Minutes(Double.parseDouble(value));
                break;
            case Recipe.EFFICIENCY:
                efficiency = new Percentage(Double.parseDouble(value));
                break;
            case Recipe.NOTES:
                notes = value;
                break;
            case Recipe.TASTE_NOTES:
                tasteNotes = value;
                break;
            case Recipe.TASTE_RATING:
            case "RATING":
                tasteRating = new BJCPTasteRating(Double.parseDouble(value));
                break;
            case Recipe.MEASURED_ORIGINAL_GRAVITY:
                measuredOriginalGravity = new SpecificGravity(Double.parseDouble(value));
                break;
            case Recipe.MEASURED_FINAL_GRAVITY:
                measuredFinalGravity = new SpecificGravity(Double.parseDouble(value));
                break;
            case Recipe.FERMENTATION_STAGES:
                fermentationStages = Integer.parseInt(value);
                break;
            case Recipe.PRIMARY_AGE:
                primaryAge = new Days(Double.parseDouble(value));
                break;
            case Recipe.PRIMARY_TEMPERATURE:
                primaryTemperature = new Celsius(Double.parseDouble(value));
                break;
            case Recipe.SECONDARY_AGE:
                secondaryAge = new Days(Double.parseDouble(value));
                break;
            case Recipe.SECONDARY_TEMPERATURE:
                secondaryTemperature = new Celsius(Double.parseDouble(value));
                break;
            case Recipe.TERTIARY_AGE:
                tertiaryAge = new Days(Double.parseDouble(value));
                break;
            case Recipe.TERTIARY_TEMPERATURE:
                tertiaryTemperature = new Celsius(Double.parseDouble(value));
                break;
            case Recipe.AGE_AFTER_BOTTLING:
                ageAfterBottling = new Days(Double.parseDouble(value));
                break;
            case Recipe.TEMPERATURE_DURING_AFTER_BOTTLING_AGE:
                temperatureDuringAfterBottlingAge = new Celsius(Double.parseDouble(value));
                break;
            case Recipe.DATE:
                date = DateTime.parse(value, Utils.getAwesomeFormatter());
                if (date == null) {
                    throw new ParseException("Unable to parse date " + value);
                }
                break;
            case Recipe.CARBONATION:
                carbonation = new CO2Volumes(Double.parseDouble(value));
                break;
            case Recipe.FORCED_CARBONATION:
                forcedCarbonation = Boolean.parseBoolean(value);
                break;
            case Recipe.CARBONATION_TEMPERATURE:
                carbonationTemperature = new Celsius(Double.parseDouble(value));
            case Recipe.PRIMING_SUGAR_NAME:
                primingSugarName = value;
                break;
            case Recipe.PRIMING_SUGAR_EQUIVALENCE:
                primingSugarEquivalence = new Factor(Double.parseDouble(value));
                break;
            case Recipe.KEG_PRIMING_FACTOR:
                kegPrimingFactor = new Factor(Double.parseDouble(value));
                break;
            case Recipe.CARBONATION_USED:
                carbonationUsed = value;
                break;
            default:
                throw new UnknownTagException("Unknown recipe tag: " + tagName);
        }
        return this;
    }

    @Override
    public Recipe create() {
        return new Recipe(name, type, style, equipment, brewer, assistantBrewer,
                batchSize, boilSize, boilTime, efficiency, hops, fermentables,
                miscs, yeasts, waters, mashProfile, notes, tasteNotes,
                tasteRating, measuredOriginalGravity, measuredFinalGravity,
                fermentationStages, primaryAge, primaryTemperature,
                secondaryAge, secondaryTemperature, tertiaryAge,
                tertiaryTemperature, ageAfterBottling,
                temperatureDuringAfterBottlingAge, date, carbonation,
                forcedCarbonation, primingSugarName, carbonationTemperature,
                primingSugarEquivalence, kegPrimingFactor, carbonationUsed);
    }
}

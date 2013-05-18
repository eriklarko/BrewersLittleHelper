package blh.core.beerxml.types.builders;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Recipe;
import blh.core.beerxml.types.Recipe.TYPE;
import blh.core.beerxml.types.Style;
import blh.core.beerxml.types.Water;
import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.builders.Builder;
import blh.core.units.BJCPTasteRating;
import blh.core.units.CO2Volumes;
import blh.core.units.Factor;
import blh.core.units.Percentage;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.temperature.Celcius;
import blh.core.units.time.Days;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

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
    private Celcius primaryTemperature;
    private Days secondaryAge;
    private Celcius secondaryTemperature;
    private Days tertiaryAge;
    private Celcius tertiaryTemperature;
    private Days ageAfterBottling;
    private Celcius temperatureDuringAfterBottlingAge;
    private Date date;
    private CO2Volumes carbonation;
    private boolean forcedCarbonation;
    private String primingSugarName;
    private Celcius carbonationTemperature;
    private Factor primingSugarEquivalence;
    private Factor kegPrimingFactor;
    private DateFormat dateFormat;

    public RecipeBuilderImpl(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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
    public RecipeBuilderImpl setPrimaryTemperature(Celcius primaryTemperature) {
        this.primaryTemperature = primaryTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setSecondaryAge(Days secondaryAge) {
        this.secondaryAge = secondaryAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setSecondaryTemperature(Celcius secondaryTemperature) {
        this.secondaryTemperature = secondaryTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTertiaryAge(Days tertiaryAge) {
        this.tertiaryAge = tertiaryAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTertiaryTemperature(Celcius tertiaryTemperature) {
        this.tertiaryTemperature = tertiaryTemperature;
        return this;
    }

    @Override
    public RecipeBuilderImpl setAgeAfterBottling(Days ageAfterBottling) {
        this.ageAfterBottling = ageAfterBottling;
        return this;
    }

    @Override
    public RecipeBuilderImpl setTemperatureDuringAfterBottlingAge(Celcius temperatureDuringAfterBottlingAge) {
        this.temperatureDuringAfterBottlingAge = temperatureDuringAfterBottlingAge;
        return this;
    }

    @Override
    public RecipeBuilderImpl setDate(Date date) {
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
    public RecipeBuilderImpl setCarbonationTemperature(Celcius carbonationTemperature) {
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
    public Builder<Recipe> set(String tagName, String value) throws ParseException{
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "TYPE":
                type = TYPE.valueOf(value.replace(" ", "_").toUpperCase());
                break;
            case "BREWER":
                brewer = value;
                break;
            case "ASST_BREWER":
                assistantBrewer = value;
                break;
            case "BATCH_SIZE":
                batchSize = new Liters(Double.parseDouble(value));
                break;
            case "BOIL_SIZE":
                boilSize = new Liters(Double.parseDouble(value));
                break;
            case "BOIL_TIME":
                boilTime = new Minutes(Integer.parseInt(value));
                break;
            case "EFFICIENCY":
                efficiency = new Percentage(Double.parseDouble(value));
                break;
            case "NOTES":
                notes = value;
                break;
            case "TASTE_NOTES":
                tasteNotes = value;
                break;
            case "TASTE_RATING":
                tasteRating = new BJCPTasteRating(Double.parseDouble(value));
                break;
            case "OG":
                measuredOriginalGravity = new SpecificGravity(Double.parseDouble(value));
                break;
            case "FG":
                measuredFinalGravity = new SpecificGravity(Double.parseDouble(value));
                break;
            case "FERMENTATION_STAGES":
                fermentationStages = Integer.parseInt(value);
                break;
            case "PRIMARY_AGE":
                primaryAge = new Days(Double.parseDouble(value));
                break;
            case "PRIMARY_TEMP":
                primaryTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "SECONDARY_AGE":
                secondaryAge = new Days(Double.parseDouble(value));
                break;
            case "SECONDARY_TEMP":
                secondaryTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "TERTIARY_AGE":
                tertiaryAge = new Days(Double.parseDouble(value));
                break;
            case "TERTIARY_TEMP":
                tertiaryTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "AGE":
                ageAfterBottling = new Days(Double.parseDouble(value));
                break;
            case "AGE_TEMP":
                temperatureDuringAfterBottlingAge = new Celcius(Double.parseDouble(value));
                break;
            case "DATE":
                try {
                    date = dateFormat.parse(value);
                } catch (java.text.ParseException ex) {
                    throw new ParseException(ex);
                }
                break;
            case "CARBONATION":
                carbonation = new CO2Volumes(Double.parseDouble(value));
                break;
            case "FORCED_CARBONATION":
                forcedCarbonation = Boolean.parseBoolean(value);
                break;
            case "PRIMING_SUGAR_NAME":
                primingSugarName = value;
                break;
            case "PRIMING_SUGAR_EQUIV":
                primingSugarEquivalence = new Factor(Double.parseDouble(value));
                break;
            case "KEG_PRIMING_FACTOR":
                kegPrimingFactor = new Factor(Double.parseDouble(value));
                break;
            default:
                System.out.println("Unknown recipe value: " + tagName);
                break;
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
                primingSugarEquivalence, kegPrimingFactor);
    }
}

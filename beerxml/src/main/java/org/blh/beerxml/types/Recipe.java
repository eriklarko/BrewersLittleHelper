package org.blh.beerxml.types;

import org.blh.beerxml.Utils;
import org.blh.core.units.BJCPTasteRating;
import org.blh.core.units.CO2Volumes;
import org.blh.core.units.Factor;
import org.blh.core.units.Percentage;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.temperature.Celsius;
import org.blh.core.units.time.Days;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author thinner
 */
public class Recipe implements BeerXMLRecord {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";
    public static final String BREWER = "BREWER";
    public static final String ASSISTANT_BREWER = "ASST_BREWER";
    public static final String BATCH_SIZE = "BATCH_SIZE";
    public static final String BOIL_SIZE = "BOIL_SIZE";
    public static final String BOIL_TIME = "BOIL_TIME";
    public static final String EFFICIENCY = "EFFICIENCY";
    public static final String NOTES = "NOTES";
    public static final String TASTE_NOTES = "TASTE_NOTES";
    public static final String TASTE_RATING = "TASTE_RATING";
    public static final String MEASURED_ORIGINAL_GRAVITY = "OG";
    public static final String MEASURED_FINAL_GRAVITY = "FG";
    public static final String FERMENTATION_STAGES = "FERMENTATION_STAGES";
    public static final String PRIMARY_AGE = "PRIMARY_AGE";
    public static final String PRIMARY_TEMPERATURE = "PRIMARY_TEMP";
    public static final String SECONDARY_AGE = "SECONDARY_AGE";
    public static final String SECONDARY_TEMPERATURE = "SECONDARY_TEMP";
    public static final String TERTIARY_AGE = "TERTIARY_AGE";
    public static final String TERTIARY_TEMPERATURE = "TERTIARY_TEMP";
    public static final String AGE_AFTER_BOTTLING = "AGE";
    public static final String TEMPERATURE_DURING_AFTER_BOTTLING_AGE = "AGE_TEMP";
    public static final String DATE = "DATE";
    public static final String CARBONATION = "CARBONATION";
    public static final String FORCED_CARBONATION = "FORCED_CARBONATION";
    public static final String PRIMING_SUGAR_NAME = "PRIMING_SUGAR_NAME";
    public static final String CARBONATION_TEMPERATURE = "CARBONATION_TEMP";
    public static final String PRIMING_SUGAR_EQUIVALENCE = "PRIMING_SUGAR_EQUIV";
    public static final String KEG_PRIMING_FACTOR = "KEG_PRIMING_FACTOR";
    public static final String CARBONATION_USED = "CARBONATION_USED";
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
    public final Celsius primaryTemperature;
    public final Days secondaryAge;
    public final Celsius secondaryTemperature;
    public final Days tertiaryAge;
    public final Celsius tertiaryTemperature;
    public final Days ageAfterBottling;
    public final Celsius temperatureDuringAfterBottlingAge;
    public final Date date;
    public final CO2Volumes carbonation;
    public final boolean forcedCarbonation;
    /**
     * Only used if forcedCarbonation is false.
     */
    public final String primingSugarName;
    public final Celsius carbonationTemperature;
    public final Factor primingSugarEquivalence;
    public final Factor kegPrimingFactor;
    public final String carbonationUsed;

    public static enum TYPE {

        EXTRACT, ALL_GRAIN, PARTIAL_MASH
    }

    public Recipe(String name, TYPE type, Style style, Equipment equipment, String brewer, String assistantBrewer, Liters batchSize, Liters boilSize, Minutes boilTime, Percentage efficiency, List<Hop> hops, List<Fermentable> fermentables, List<Misc> miscs, List<Yeast> yeasts, List<Water> waters, MashProfile mashProfile, String notes, String tasteNotes, BJCPTasteRating tasteRating, SpecificGravity measuredOriginalGravity, SpecificGravity measuredFinalGravity, int fermentationStages, Days primaryAge, Celsius primaryTemperature, Days secondaryAge, Celsius secondaryTemperature, Days tertiaryAge, Celsius tertiaryTemperature, Days ageAfterBottling, Celsius temperatureDuringAfterBottlingAge, Date date, CO2Volumes carbonation, boolean forcedCarbonation, String primingSugarName, Celsius carbonationTemperature, Factor primingSugarEquivalence, Factor kegPrimingFactor, String carbonationUsed) {
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
        this.carbonationUsed = carbonationUsed;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(TYPE, Utils.toStringOrNull(type));

        tagsAndValues.put(BREWER, Utils.toStringOrNull(brewer));
        tagsAndValues.put(ASSISTANT_BREWER, Utils.toStringOrNull(assistantBrewer));
        tagsAndValues.put(BATCH_SIZE, Utils.toStringOrNull(batchSize));
        tagsAndValues.put(BOIL_SIZE, Utils.toStringOrNull(boilSize));
        tagsAndValues.put(BOIL_TIME, Utils.toStringOrNull(boilTime));
        tagsAndValues.put(EFFICIENCY, Utils.toStringOrNull(efficiency));
        tagsAndValues.put(NOTES, Utils.toStringOrNull(notes));
        tagsAndValues.put(TASTE_NOTES, Utils.toStringOrNull(tasteNotes));
        tagsAndValues.put(TASTE_RATING, Utils.toStringOrNull(tasteRating));
        tagsAndValues.put(MEASURED_ORIGINAL_GRAVITY, Utils.toStringOrNull(measuredOriginalGravity));
        tagsAndValues.put(MEASURED_FINAL_GRAVITY, Utils.toStringOrNull(measuredFinalGravity));
        tagsAndValues.put(FERMENTATION_STAGES, Utils.toStringOrNull(fermentationStages));
        tagsAndValues.put(PRIMARY_AGE, Utils.toStringOrNull(primaryAge));
        tagsAndValues.put(PRIMARY_TEMPERATURE, Utils.toStringOrNull(primaryTemperature));
        tagsAndValues.put(SECONDARY_AGE, Utils.toStringOrNull(secondaryAge));
        tagsAndValues.put(SECONDARY_TEMPERATURE, Utils.toStringOrNull(secondaryTemperature));
        tagsAndValues.put(TERTIARY_AGE, Utils.toStringOrNull(tertiaryAge));
        tagsAndValues.put(TERTIARY_TEMPERATURE, Utils.toStringOrNull(tertiaryTemperature));
        tagsAndValues.put(AGE_AFTER_BOTTLING, Utils.toStringOrNull(ageAfterBottling));
        tagsAndValues.put(TEMPERATURE_DURING_AFTER_BOTTLING_AGE, Utils.toStringOrNull(temperatureDuringAfterBottlingAge));
        tagsAndValues.put(DATE, Utils.toStringOrNull(date));
        tagsAndValues.put(CARBONATION, Utils.toStringOrNull(carbonation));
        tagsAndValues.put(FORCED_CARBONATION, Utils.toStringOrNull(forcedCarbonation));
        tagsAndValues.put(PRIMING_SUGAR_NAME, Utils.toStringOrNull(primingSugarName));
        tagsAndValues.put(CARBONATION_TEMPERATURE, Utils.toStringOrNull(carbonationTemperature));
        tagsAndValues.put(PRIMING_SUGAR_EQUIVALENCE, Utils.toStringOrNull(primingSugarEquivalence));
        tagsAndValues.put(KEG_PRIMING_FACTOR, Utils.toStringOrNull(kegPrimingFactor));
        tagsAndValues.put(CARBONATION_USED, Utils.toStringOrNull(carbonationUsed));

        return tagsAndValues;
    }

    @Override
    public List<BeerXMLRecord> getSubRecords() {
        List<BeerXMLRecord> records = new LinkedList<>();
        records.add(this.style);
        records.add(this.equipment);
        records.add(mashProfile);

        return records;
    }

    @Override
    public List<BeerXMLRecordSet> getSubRecordSets() {
        List<BeerXMLRecordSet> recordSets = new LinkedList<>();

        BeerXMLRecordSet<Hop> hops = new BeerXMLRecordSet<>(Hop.class, this.hops);
        BeerXMLRecordSet<Fermentable> fermentables = new BeerXMLRecordSet<>(Fermentable.class, this.fermentables);
        BeerXMLRecordSet<Misc> miscs = new BeerXMLRecordSet<>(Misc.class, this.miscs);
        BeerXMLRecordSet<Yeast> yeasts = new BeerXMLRecordSet<>(Yeast.class, this.yeasts);
        BeerXMLRecordSet<Water> waters = new BeerXMLRecordSet<>(Water.class, this.waters);

        recordSets.add(hops);
        recordSets.add(fermentables);
        recordSets.add(miscs);
        recordSets.add(yeasts);
        recordSets.add(waters);

        return recordSets;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.style, other.style)) {
            return false;
        }
        if (!Objects.equals(this.equipment, other.equipment)) {
            return false;
        }
        if (!Objects.equals(this.brewer, other.brewer)) {
            return false;
        }
        if (!Objects.equals(this.assistantBrewer, other.assistantBrewer)) {
            return false;
        }
        if (!Objects.equals(this.batchSize, other.batchSize)) {
            return false;
        }
        if (!Objects.equals(this.boilSize, other.boilSize)) {
            return false;
        }
        if (!Objects.equals(this.boilTime, other.boilTime)) {
            return false;
        }
        if (!Objects.equals(this.efficiency, other.efficiency)) {
            return false;
        }
        if (!Objects.equals(this.hops, other.hops)) {
            return false;
        }
        if (!Objects.equals(this.fermentables, other.fermentables)) {
            return false;
        }
        if (!Objects.equals(this.miscs, other.miscs)) {
            return false;
        }
        if (!Objects.equals(this.yeasts, other.yeasts)) {
            return false;
        }
        if (!Objects.equals(this.waters, other.waters)) {
            return false;
        }
        if (!Objects.equals(this.mashProfile, other.mashProfile)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.tasteNotes, other.tasteNotes)) {
            return false;
        }
        if (!Objects.equals(this.tasteRating, other.tasteRating)) {
            return false;
        }
        if (!Objects.equals(this.measuredOriginalGravity, other.measuredOriginalGravity)) {
            return false;
        }
        if (!Objects.equals(this.measuredFinalGravity, other.measuredFinalGravity)) {
            return false;
        }
        if (this.fermentationStages != other.fermentationStages) {
            return false;
        }
        if (!Objects.equals(this.primaryAge, other.primaryAge)) {
            return false;
        }
        if (!Objects.equals(this.primaryTemperature, other.primaryTemperature)) {
            return false;
        }
        if (!Objects.equals(this.secondaryAge, other.secondaryAge)) {
            return false;
        }
        if (!Objects.equals(this.secondaryTemperature, other.secondaryTemperature)) {
            return false;
        }
        if (!Objects.equals(this.tertiaryAge, other.tertiaryAge)) {
            return false;
        }
        if (!Objects.equals(this.tertiaryTemperature, other.tertiaryTemperature)) {
            return false;
        }
        if (!Objects.equals(this.ageAfterBottling, other.ageAfterBottling)) {
            return false;
        }
        if (!Objects.equals(this.temperatureDuringAfterBottlingAge, other.temperatureDuringAfterBottlingAge)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.carbonation, other.carbonation)) {
            return false;
        }
        if (this.forcedCarbonation != other.forcedCarbonation) {
            return false;
        }
        if (!Objects.equals(this.primingSugarName, other.primingSugarName)) {
            return false;
        }
        if (!Objects.equals(this.carbonationTemperature, other.carbonationTemperature)) {
            return false;
        }
        if (!Objects.equals(this.primingSugarEquivalence, other.primingSugarEquivalence)) {
            return false;
        }
        if (!Objects.equals(this.kegPrimingFactor, other.kegPrimingFactor)) {
            return false;
        }
        if (!Objects.equals(this.carbonationUsed, other.carbonationUsed)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 41 * hash + Objects.hashCode(this.style);
        hash = 41 * hash + Objects.hashCode(this.equipment);
        hash = 41 * hash + Objects.hashCode(this.brewer);
        hash = 41 * hash + Objects.hashCode(this.assistantBrewer);
        hash = 41 * hash + Objects.hashCode(this.batchSize);
        hash = 41 * hash + Objects.hashCode(this.boilSize);
        hash = 41 * hash + Objects.hashCode(this.boilTime);
        hash = 41 * hash + Objects.hashCode(this.efficiency);
        hash = 41 * hash + Objects.hashCode(this.hops);
        hash = 41 * hash + Objects.hashCode(this.fermentables);
        hash = 41 * hash + Objects.hashCode(this.miscs);
        hash = 41 * hash + Objects.hashCode(this.yeasts);
        hash = 41 * hash + Objects.hashCode(this.waters);
        hash = 41 * hash + Objects.hashCode(this.mashProfile);
        hash = 41 * hash + Objects.hashCode(this.notes);
        hash = 41 * hash + Objects.hashCode(this.tasteNotes);
        hash = 41 * hash + Objects.hashCode(this.tasteRating);
        hash = 41 * hash + Objects.hashCode(this.measuredOriginalGravity);
        hash = 41 * hash + Objects.hashCode(this.measuredFinalGravity);
        hash = 41 * hash + this.fermentationStages;
        hash = 41 * hash + Objects.hashCode(this.primaryAge);
        hash = 41 * hash + Objects.hashCode(this.primaryTemperature);
        hash = 41 * hash + Objects.hashCode(this.secondaryAge);
        hash = 41 * hash + Objects.hashCode(this.secondaryTemperature);
        hash = 41 * hash + Objects.hashCode(this.tertiaryAge);
        hash = 41 * hash + Objects.hashCode(this.tertiaryTemperature);
        hash = 41 * hash + Objects.hashCode(this.ageAfterBottling);
        hash = 41 * hash + Objects.hashCode(this.temperatureDuringAfterBottlingAge);
        hash = 41 * hash + Objects.hashCode(this.date);
        hash = 41 * hash + Objects.hashCode(this.carbonation);
        hash = 41 * hash + (this.forcedCarbonation ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.primingSugarName);
        hash = 41 * hash + Objects.hashCode(this.carbonationTemperature);
        hash = 41 * hash + Objects.hashCode(this.primingSugarEquivalence);
        hash = 41 * hash + Objects.hashCode(this.kegPrimingFactor);
        hash = 41 * hash + Objects.hashCode(this.carbonationUsed);
        return hash;
    }
}

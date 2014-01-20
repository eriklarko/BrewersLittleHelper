package org.blh.beerxml.type.builder;

import java.util.List;
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
 * @author thinner
 */
public interface RecipeBuilder extends Builder<Recipe> {

    RecipeBuilder setAgeAfterBottling(Days ageAfterBottling);

    RecipeBuilder setAssistantBrewer(String assistantBrewer);

    RecipeBuilder setBatchSize(Liters batchSize);

    RecipeBuilder setBoilSize(Liters boilSize);

    RecipeBuilder setBoilTime(Minutes boilTime);

    RecipeBuilder setBrewer(String brewer);

    RecipeBuilder setCarbonation(CO2Volumes carbonation);

    RecipeBuilder setCarbonationTemperature(Celsius carbonationTemperature);

    RecipeBuilder setDate(DateTime date);

    RecipeBuilder setEfficiency(Percentage efficiency);

    RecipeBuilder setEquipment(Equipment equipment);

    RecipeBuilder setFermentables(List<Fermentable> fermentables);

    RecipeBuilder setFermentationStages(int fermentationStages);

    RecipeBuilder setForcedCarbonation(boolean forcedCarbonation);

    RecipeBuilder setHops(List<Hop> hops);

    RecipeBuilder setKegPrimingFactor(Factor kegPrimingFactor);

    RecipeBuilder setMashProfile(MashProfile mashProfile);

    RecipeBuilder setMeasuredFinalGravity(SpecificGravity measuredFinalGravity);

    RecipeBuilder setMeasuredOriginalGravity(SpecificGravity measuredOriginalGravity);

    RecipeBuilder setMiscs(List<Misc> miscs);

    RecipeBuilder setName(String name);

    RecipeBuilder setNotes(String notes);

    RecipeBuilder setPrimaryAge(Days primaryAge);

    RecipeBuilder setPrimaryTemperature(Celsius primaryTemperature);

    RecipeBuilder setPrimingSugarEquivalence(Factor primingSugarEquivalence);

    RecipeBuilder setPrimingSugarName(String primingSugarName);

    RecipeBuilder setSecondaryAge(Days secondaryAge);

    RecipeBuilder setSecondaryTemperature(Celsius secondaryTemperature);

    RecipeBuilder setStyle(Style style);

    RecipeBuilder setTasteNotes(String tasteNotes);

    RecipeBuilder setTasteRating(BJCPTasteRating tasteRating);

    RecipeBuilder setTemperatureDuringAfterBottlingAge(Celsius temperatureDuringAfterBottlingAge);

    RecipeBuilder setTertiaryAge(Days tertiaryAge);

    RecipeBuilder setTertiaryTemperature(Celsius tertiaryTemperature);

    RecipeBuilder setType(TYPE type);

    RecipeBuilder setWaters(List<Water> waters);

    RecipeBuilder setYeasts(List<Yeast> yeasts);

    RecipeBuilder setCarbonationUsed(String carbonationUsed);

}

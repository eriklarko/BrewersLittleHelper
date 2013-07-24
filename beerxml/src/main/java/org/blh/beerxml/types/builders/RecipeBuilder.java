/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import java.util.List;
import org.blh.beerxml.types.Equipment;
import org.blh.beerxml.types.Fermentable;
import org.blh.beerxml.types.Hop;
import org.blh.beerxml.types.MashProfile;
import org.blh.beerxml.types.Misc;
import org.blh.beerxml.types.Recipe;
import org.blh.beerxml.types.Recipe.TYPE;
import org.blh.beerxml.types.Style;
import org.blh.beerxml.types.Water;
import org.blh.beerxml.types.Yeast;
import org.blh.core.units.BJCPTasteRating;
import org.blh.core.units.CO2Volumes;
import org.blh.core.units.Factor;
import org.blh.core.units.Percentage;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.temperature.Celsius;
import org.blh.core.units.time.Days;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.joda.time.DateTime;

/**
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

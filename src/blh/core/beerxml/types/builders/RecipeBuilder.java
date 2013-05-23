/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

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
import java.util.Date;
import java.util.List;

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

    RecipeBuilder setCarbonationTemperature(Celcius carbonationTemperature);

    RecipeBuilder setDate(Date date);

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

    RecipeBuilder setPrimaryTemperature(Celcius primaryTemperature);

    RecipeBuilder setPrimingSugarEquivalence(Factor primingSugarEquivalence);

    RecipeBuilder setPrimingSugarName(String primingSugarName);

    RecipeBuilder setSecondaryAge(Days secondaryAge);

    RecipeBuilder setSecondaryTemperature(Celcius secondaryTemperature);

    RecipeBuilder setStyle(Style style);

    RecipeBuilder setTasteNotes(String tasteNotes);

    RecipeBuilder setTasteRating(BJCPTasteRating tasteRating);

    RecipeBuilder setTemperatureDuringAfterBottlingAge(Celcius temperatureDuringAfterBottlingAge);

    RecipeBuilder setTertiaryAge(Days tertiaryAge);

    RecipeBuilder setTertiaryTemperature(Celcius tertiaryTemperature);

    RecipeBuilder setType(TYPE type);

    RecipeBuilder setWaters(List<Water> waters);

    RecipeBuilder setYeasts(List<Yeast> yeasts);
    
    RecipeBuilder setCarbonationUsed(String carbonationUsed);
    
}

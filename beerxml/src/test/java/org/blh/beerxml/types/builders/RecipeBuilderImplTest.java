package org.blh.beerxml.types.builders;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.Utils;
import org.blh.beerxml.types.Recipe;
import org.blh.beerxml.types.Recipe.TYPE;
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
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Assert;
import org.junit.Test;

public class RecipeBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        TYPE type = TYPE.ALL_GRAIN;
        String brewer = "5";
        String assistantBrewer = "6";
        Liters batchSize = new Liters(7);
        Liters boilSize = new Liters(8);
        Minutes boilTime = new Minutes(9);
        Percentage efficiency = new Percentage(10);
        String notes = "17";
        String tasteNotes = "18";
        BJCPTasteRating tasteRating = new BJCPTasteRating(19);
        SpecificGravity measuredOriginalGravity = new SpecificGravity(20);
        SpecificGravity measuredFinalGravity = new SpecificGravity(21);
        int fermentationStages = 22;
        Days primaryAge = new Days(23);
        Celsius primaryTemperature = new Celsius(24);
        Days secondaryAge = new Days(25);
        Celsius secondaryTemperature = new Celsius(26);
        Days tertiaryAge = new Days(27);
        Celsius tertiaryTemperature = new Celsius(28);
        Days ageAfterBottling = new Days(29);
        Celsius temperatureDuringAfterBottlingAge = new Celsius(30);
        DateTime date = new DateTime();
        CO2Volumes carbonation = new CO2Volumes(32);
        boolean forcedCarbonation = true;
        String primingSugarName = "34";
        Celsius carbonationTemperature = new Celsius(35);
        Factor primingSugarEquivalence = new Factor(36);
        Factor kegPrimingFactor = new Factor(37);
        String carbonationUsed = "38";

        BuilderUtils.addTag(tags, Recipe.NAME, name);
        BuilderUtils.addTag(tags, Recipe.TYPE, type.toString());
        BuilderUtils.addTag(tags, Recipe.BREWER, brewer);
        BuilderUtils.addTag(tags, Recipe.ASSISTANT_BREWER, assistantBrewer);
        BuilderUtils.addTag(tags, Recipe.BATCH_SIZE, batchSize);
        BuilderUtils.addTag(tags, Recipe.BOIL_SIZE, boilSize);
        BuilderUtils.addTag(tags, Recipe.BOIL_TIME, boilTime);
        BuilderUtils.addTag(tags, Recipe.EFFICIENCY, efficiency);
        BuilderUtils.addTag(tags, Recipe.NOTES, notes);
        BuilderUtils.addTag(tags, Recipe.TASTE_NOTES, tasteNotes);
        BuilderUtils.addTag(tags, Recipe.TASTE_RATING, tasteRating);
        BuilderUtils.addTag(tags, Recipe.MEASURED_ORIGINAL_GRAVITY, measuredOriginalGravity);
        BuilderUtils.addTag(tags, Recipe.MEASURED_FINAL_GRAVITY, measuredFinalGravity);
        BuilderUtils.addTag(tags, Recipe.FERMENTATION_STAGES, fermentationStages);
        BuilderUtils.addTag(tags, Recipe.PRIMARY_AGE, primaryAge);
        BuilderUtils.addTag(tags, Recipe.PRIMARY_TEMPERATURE, primaryTemperature);
        BuilderUtils.addTag(tags, Recipe.SECONDARY_AGE, secondaryAge);
        BuilderUtils.addTag(tags, Recipe.SECONDARY_TEMPERATURE, secondaryTemperature);
        BuilderUtils.addTag(tags, Recipe.TERTIARY_AGE, tertiaryAge);
        BuilderUtils.addTag(tags, Recipe.TERTIARY_TEMPERATURE, tertiaryTemperature);
        BuilderUtils.addTag(tags, Recipe.AGE_AFTER_BOTTLING, ageAfterBottling);
        BuilderUtils.addTag(tags, Recipe.TEMPERATURE_DURING_AFTER_BOTTLING_AGE, temperatureDuringAfterBottlingAge);
        BuilderUtils.addTag(tags, Recipe.DATE, Utils.getAwesomeFormatter().print(date));
        BuilderUtils.addTag(tags, Recipe.CARBONATION, carbonation);
        BuilderUtils.addTag(tags, Recipe.FORCED_CARBONATION, forcedCarbonation);
        BuilderUtils.addTag(tags, Recipe.PRIMING_SUGAR_NAME, primingSugarName);
        BuilderUtils.addTag(tags, Recipe.CARBONATION_TEMPERATURE, carbonationTemperature);
        BuilderUtils.addTag(tags, Recipe.PRIMING_SUGAR_EQUIVALENCE, primingSugarEquivalence);
        BuilderUtils.addTag(tags, Recipe.KEG_PRIMING_FACTOR, kegPrimingFactor);
        BuilderUtils.addTag(tags, Recipe.CARBONATION_USED, carbonationUsed);

        RecipeBuilderImpl builder = new RecipeBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (ParseException ex) {
                Assert.fail(ex.getMessage());
            }
        }
        Recipe actual = builder.create();
        Recipe expected = new Recipe(name, type, null, null, brewer, assistantBrewer, batchSize, boilSize, boilTime, efficiency, null, null, null, null, null, null, notes, tasteNotes, tasteRating, measuredOriginalGravity, measuredFinalGravity, fermentationStages, primaryAge, primaryTemperature, secondaryAge, secondaryTemperature, tertiaryAge, tertiaryTemperature, ageAfterBottling, temperatureDuringAfterBottlingAge, date, carbonation, forcedCarbonation, primingSugarName, carbonationTemperature, primingSugarEquivalence, kegPrimingFactor, carbonationUsed);
        assertEquality(expected, actual);
    }

    private void assertEquality(Recipe expected, Recipe actual) {
        Assert.assertEquals("Name", expected.name, actual.name);
        Assert.assertEquals("Type", expected.type, actual.type);
        Assert.assertEquals("Style", expected.style, actual.style);
        Assert.assertEquals("Equipment", expected.equipment, actual.equipment);
        Assert.assertEquals("Brewer", expected.brewer, actual.brewer);
        Assert.assertEquals("Ass brewer", expected.assistantBrewer, actual.assistantBrewer);
        Assert.assertEquals("Batch size", expected.batchSize, actual.batchSize);
        Assert.assertEquals("Boil size", expected.boilSize, actual.boilSize);
        Assert.assertEquals("Boil time", expected.boilTime, actual.boilTime);
        Assert.assertEquals("Efficiency", expected.efficiency, actual.efficiency);
        Assert.assertEquals("Hops", expected.hops, actual.hops);
        Assert.assertEquals("Fermentables", expected.fermentables, actual.fermentables);
        Assert.assertEquals("Miscs", expected.miscs, actual.miscs);
        Assert.assertEquals("Yeasts", expected.yeasts, actual.yeasts);
        Assert.assertEquals("Waters", expected.waters, actual.waters);
        Assert.assertEquals("Mash profile", expected.mashProfile, actual.mashProfile);
        Assert.assertEquals("Notes", expected.notes, actual.notes);
        Assert.assertEquals("Taste notes", expected.tasteNotes, actual.tasteNotes);
        Assert.assertEquals("Taste rating", expected.tasteRating, actual.tasteRating);
        Assert.assertEquals("meas og", expected.measuredOriginalGravity, actual.measuredOriginalGravity);
        Assert.assertEquals("meas fg", expected.measuredFinalGravity, actual.measuredFinalGravity);
        Assert.assertEquals("Ferm stages", expected.fermentationStages, actual.fermentationStages);
        Assert.assertEquals("Prim age", expected.primaryAge, actual.primaryAge);
        Assert.assertEquals("Prim temp", expected.primaryTemperature, actual.primaryTemperature);
        Assert.assertEquals("Sec age", expected.secondaryAge, actual.secondaryAge);
        Assert.assertEquals("Sec temp", expected.secondaryTemperature, actual.secondaryTemperature);
        Assert.assertEquals("Tert age", expected.tertiaryAge, actual.tertiaryAge);
        Assert.assertEquals("Tert temp", expected.tertiaryTemperature, actual.tertiaryTemperature);
        Assert.assertEquals("Age", expected.ageAfterBottling, actual.ageAfterBottling);
        Assert.assertEquals("Bottle age temp", expected.temperatureDuringAfterBottlingAge, actual.temperatureDuringAfterBottlingAge);
        //Assert.assertTrue("Date", Math.abs(expected.date.getTime() - actual.date.getTime()) < 1000);
        Assert.assertEquals("Date", expected.date, actual.date);
        Assert.assertEquals("Carbonation", expected.carbonation, actual.carbonation);
        Assert.assertEquals("Forced carb", expected.forcedCarbonation, actual.forcedCarbonation);
        Assert.assertEquals("Priming sugar name", expected.primingSugarName, actual.primingSugarName);
        Assert.assertEquals("Carb temp", expected.carbonationTemperature, actual.carbonationTemperature);
        Assert.assertEquals("Prim sug eq", expected.primingSugarEquivalence, actual.primingSugarEquivalence);
        Assert.assertEquals("Keg priming", expected.kegPrimingFactor, actual.kegPrimingFactor);
        Assert.assertEquals("Carbonation used", expected.carbonationUsed, actual.carbonationUsed);
    }
    
    @Test
    public void testSetUnknownTag() {
        try {
            Builder builder = new YeastBuilderImpl();
            builder.set("ökldafs", "");
            Assert.fail();
        }  catch (UnknownTagException ex) {
            Assert.assertTrue(true);
        } catch(ParseException ex) {
            Assert.fail();
        }
    }
}
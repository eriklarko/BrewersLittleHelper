package org.blh.beerxml.types.builders;

import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import org.blh.core.units.*;
import org.blh.beerxml.types.*;
import org.blh.beerxml.types.Recipe.TYPE;
import org.blh.beerxml.types.builders.*;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.temperature.Celsius;
import org.blh.core.units.time.Days;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecipeBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);

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
        Date date = Calendar.getInstance().getTime();
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
        BuilderUtils.addTag(tags, Recipe.DATE, dateFormat.format(date));
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
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Recipe actual = builder.create();
        Recipe expected = new Recipe(name, type, null, null, brewer, assistantBrewer, batchSize, boilSize, boilTime, efficiency, null, null, null, null, null, null, notes, tasteNotes, tasteRating, measuredOriginalGravity, measuredFinalGravity, fermentationStages, primaryAge, primaryTemperature, secondaryAge, secondaryTemperature, tertiaryAge, tertiaryTemperature, ageAfterBottling, temperatureDuringAfterBottlingAge, date, carbonation, forcedCarbonation, primingSugarName, carbonationTemperature, primingSugarEquivalence, kegPrimingFactor, carbonationUsed);
        assertEquality(expected, actual);
    }

    private void assertEquality(Recipe expected, Recipe actual) {
        Assert.assertEquals(expected.name, actual.name);
        Assert.assertEquals(expected.type, actual.type);
        Assert.assertEquals(expected.style, actual.style);
        Assert.assertEquals(expected.equipment, actual.equipment);
        Assert.assertEquals(expected.brewer, actual.brewer);
        Assert.assertEquals(expected.assistantBrewer, actual.assistantBrewer);
        Assert.assertEquals(expected.batchSize, actual.batchSize);
        Assert.assertEquals(expected.boilSize, actual.boilSize);
        Assert.assertEquals(expected.boilTime, actual.boilTime);
        Assert.assertEquals(expected.efficiency, actual.efficiency);
        Assert.assertEquals(expected.hops, actual.hops);
        Assert.assertEquals(expected.fermentables, actual.fermentables);
        Assert.assertEquals(expected.miscs, actual.miscs);
        Assert.assertEquals(expected.yeasts, actual.yeasts);
        Assert.assertEquals(expected.waters, actual.waters);
        Assert.assertEquals(expected.mashProfile, actual.mashProfile);
        Assert.assertEquals(expected.notes, actual.notes);
        Assert.assertEquals(expected.tasteNotes, actual.tasteNotes);
        Assert.assertEquals(expected.tasteRating, actual.tasteRating);
        Assert.assertEquals(expected.measuredOriginalGravity, actual.measuredOriginalGravity);
        Assert.assertEquals(expected.measuredFinalGravity, actual.measuredFinalGravity);
        Assert.assertEquals(expected.fermentationStages, actual.fermentationStages);
        Assert.assertEquals(expected.primaryAge, actual.primaryAge);
        Assert.assertEquals(expected.primaryTemperature, actual.primaryTemperature);
        Assert.assertEquals(expected.secondaryAge, actual.secondaryAge);
        Assert.assertEquals(expected.secondaryTemperature, actual.secondaryTemperature);
        Assert.assertEquals(expected.tertiaryAge, actual.tertiaryAge);
        Assert.assertEquals(expected.tertiaryTemperature, actual.tertiaryTemperature);
        Assert.assertEquals(expected.ageAfterBottling, actual.ageAfterBottling);
        Assert.assertEquals(expected.temperatureDuringAfterBottlingAge, actual.temperatureDuringAfterBottlingAge);
        Assert.assertTrue(Math.abs(expected.date.getTime() - actual.date.getTime()) < 1000);
        Assert.assertEquals(expected.carbonation, actual.carbonation);
        Assert.assertEquals(expected.forcedCarbonation, actual.forcedCarbonation);
        Assert.assertEquals(expected.primingSugarName, actual.primingSugarName);
        Assert.assertEquals(expected.carbonationTemperature, actual.carbonationTemperature);
        Assert.assertEquals(expected.primingSugarEquivalence, actual.primingSugarEquivalence);
        Assert.assertEquals(expected.kegPrimingFactor, actual.kegPrimingFactor);
        Assert.assertEquals(expected.carbonationUsed, actual.carbonationUsed);
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
package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.Utils;
import org.blh.beerxml.type.Recipe;
import org.blh.beerxml.type.Recipe.TYPE;
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
        Assert.assertEquals("Name", expected.getName(), actual.getName());
        Assert.assertEquals("Type", expected.getType(), actual.getType());
        Assert.assertEquals("Style", expected.getStyle(), actual.getStyle());
        Assert.assertEquals("Equipment", expected.getEquipment(), actual.getEquipment());
        Assert.assertEquals("Brewer", expected.getBrewer(), actual.getBrewer());
        Assert.assertEquals("Ass brewer", expected.getAssistantBrewer(), actual.getAssistantBrewer());
        Assert.assertEquals("Batch size", expected.getBatchSize(), actual.getBatchSize());
        Assert.assertEquals("Boil size", expected.getBoilSize(), actual.getBoilSize());
        Assert.assertEquals("Boil time", expected.getBoilTime(), actual.getBoilTime());
        Assert.assertEquals("Efficiency", expected.getEfficiency(), actual.getEfficiency());
        Assert.assertEquals("Hops", expected.getHops(), actual.getHops());
        Assert.assertEquals("Fermentables", expected.getFermentables(), actual.getFermentables());
        Assert.assertEquals("Miscs", expected.getMiscs(), actual.getMiscs());
        Assert.assertEquals("Yeasts", expected.getYeasts(), actual.getYeasts());
        Assert.assertEquals("Waters", expected.getWaters(), actual.getWaters());
        Assert.assertEquals("Mash profile", expected.getMashProfile(), actual.getMashProfile());
        Assert.assertEquals("Notes", expected.getNotes(), actual.getNotes());
        Assert.assertEquals("Taste notes", expected.getTasteNotes(), actual.getTasteNotes());
        Assert.assertEquals("Taste rating", expected.getTasteRating(), actual.getTasteRating());
        Assert.assertEquals("meas og", expected.getMeasuredOriginalGravity(), actual.getMeasuredOriginalGravity());
        Assert.assertEquals("meas fg", expected.getMeasuredFinalGravity(), actual.getMeasuredFinalGravity());
        Assert.assertEquals("Ferm stages", expected.getFermentationStages(), actual.getFermentationStages());
        Assert.assertEquals("Prim age", expected.getPrimaryAge(), actual.getPrimaryAge());
        Assert.assertEquals("Prim temp", expected.getPrimaryTemperature(), actual.getPrimaryTemperature());
        Assert.assertEquals("Sec age", expected.getSecondaryAge(), actual.getSecondaryAge());
        Assert.assertEquals("Sec temp", expected.getSecondaryTemperature(), actual.getSecondaryTemperature());
        Assert.assertEquals("Tert age", expected.getTertiaryAge(), actual.getTertiaryAge());
        Assert.assertEquals("Tert temp", expected.getTertiaryTemperature(), actual.getTertiaryTemperature());
        Assert.assertEquals("Age", expected.getAgeAfterBottling(), actual.getAgeAfterBottling());
        Assert.assertEquals("Bottle age temp", expected.getTemperatureDuringAfterBottlingAge(), actual.getTemperatureDuringAfterBottlingAge());
        //Assert.assertTrue("Date", Math.abs(expected.getdate.getTime() - actual.getdate.getTime()) < 1000());
        Assert.assertEquals("Date", expected.getDate(), actual.getDate());
        Assert.assertEquals("Carbonation", expected.getCarbonation(), actual.getCarbonation());
        Assert.assertEquals("Forced carb", expected.isForcedCarbonation(), actual.isForcedCarbonation());
        Assert.assertEquals("Priming sugar name", expected.getPrimingSugarName(), actual.getPrimingSugarName());
        Assert.assertEquals("Carb temp", expected.getCarbonationTemperature(), actual.getCarbonationTemperature());
        Assert.assertEquals("Prim sug eq", expected.getPrimingSugarEquivalence(), actual.getPrimingSugarEquivalence());
        Assert.assertEquals("Keg priming", expected.getKegPrimingFactor(), actual.getKegPrimingFactor());
        Assert.assertEquals("Carbonation used", expected.getCarbonationUsed(), actual.getCarbonationUsed());
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
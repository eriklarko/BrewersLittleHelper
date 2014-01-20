package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Style.TYPE;
import org.blh.core.unit.CO2Volumes;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.color.SRM;
import org.blh.core.unit.gravity.SpecificGravity;
import org.junit.Assert;
import org.junit.Test;

public class StyleBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        String category = "2";
        String categoryNumber = "3";
        String styleLetter = "4";
        String styleGuide = "5";
        TYPE type = TYPE.ALE;
        SpecificGravity originalGravityMin = new SpecificGravity(7);
        SpecificGravity originalGravityMax = new SpecificGravity(8);
        SpecificGravity finalGravityMin = new SpecificGravity(9);
        SpecificGravity finalGravityMax = new SpecificGravity(10);
        IBU IBUMin = new IBU(11);
        IBU IBUMax = new IBU(12);
        SRM colorMin = new SRM(13);
        SRM colorMax = new SRM(14);
        CO2Volumes carbonationMin = new CO2Volumes(15);
        CO2Volumes carbonationMax = new CO2Volumes(16);
        ABV alcoholMin = new ABV(17);
        ABV alcoholMax = new ABV(18);
        String notes = "19";
        String profile = "20";
        String ingredients = "21";
        String examples = "22";

        BuilderUtils.addTag(tags, Style.NAME, name);
        BuilderUtils.addTag(tags, Style.CATEGORY, category);
        BuilderUtils.addTag(tags, Style.CATEGORY_NUMBER, categoryNumber);
        BuilderUtils.addTag(tags, Style.STYLE_LETTER, styleLetter);
        BuilderUtils.addTag(tags, Style.STYLE_GUIDE, styleGuide);
        BuilderUtils.addTag(tags, Style.TYPE, type.toString());
        BuilderUtils.addTag(tags, Style.ORIGINAL_GRAVITY_MIN, originalGravityMin);
        BuilderUtils.addTag(tags, Style.ORIGINAL_GRAVITY_MAX, originalGravityMax);
        BuilderUtils.addTag(tags, Style.FINAL_GRAVITY_MIN, finalGravityMin);
        BuilderUtils.addTag(tags, Style.FINAL_GRAVITY_MAX, finalGravityMax);
        BuilderUtils.addTag(tags, Style.IBU_MIN, IBUMin);
        BuilderUtils.addTag(tags, Style.IBU_MAX, IBUMax);
        BuilderUtils.addTag(tags, Style.COLOR_MIN, colorMin);
        BuilderUtils.addTag(tags, Style.COLOR_MAX, colorMax);
        BuilderUtils.addTag(tags, Style.CARBONATION_MIN, carbonationMin);
        BuilderUtils.addTag(tags, Style.CARBONATION_MAX, carbonationMax);
        BuilderUtils.addTag(tags, Style.ALCOHOL_MIN, alcoholMin);
        BuilderUtils.addTag(tags, Style.ALCOHOL_MAX, alcoholMax);
        BuilderUtils.addTag(tags, Style.NOTES, notes);
        BuilderUtils.addTag(tags, Style.PROFILE, profile);
        BuilderUtils.addTag(tags, Style.INGREDIENTS, ingredients);
        BuilderUtils.addTag(tags, Style.EXAMPLES, examples);
        StyleBuilderImpl builder = new StyleBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Style actual = builder.create();
        Style expected = new Style(name, category, categoryNumber, styleLetter, styleGuide, type, originalGravityMin, originalGravityMax, finalGravityMin, finalGravityMax, IBUMin, IBUMax, colorMin, colorMax, carbonationMin, carbonationMax, alcoholMin, alcoholMax, notes, profile, ingredients, examples);
        assertEquality(expected, actual);
    }

    private void assertEquality(Style expected, Style actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getCategory(), actual.getCategory());
        Assert.assertEquals(expected.getCategoryNumber(), actual.getCategoryNumber());
        Assert.assertEquals(expected.getStyleLetter(), actual.getStyleLetter());
        Assert.assertEquals(expected.getStyleGuide(), actual.getStyleGuide());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getOriginalGravityMin(), actual.getOriginalGravityMin());
        Assert.assertEquals(expected.getOriginalGravityMax(), actual.getOriginalGravityMax());
        Assert.assertEquals(expected.getFinalGravityMin(), actual.getFinalGravityMin());
        Assert.assertEquals(expected.getFinalGravityMax(), actual.getFinalGravityMax());
        Assert.assertEquals(expected.getIBUMin(), actual.getIBUMin());
        Assert.assertEquals(expected.getIBUMax(), actual.getIBUMax());
        Assert.assertEquals(expected.getColorMin(), actual.getColorMin());
        Assert.assertEquals(expected.getColorMax(), actual.getColorMax());
        Assert.assertEquals(expected.getCarbonationMin(), actual.getCarbonationMin());
        Assert.assertEquals(expected.getCarbonationMax(), actual.getCarbonationMax());
        Assert.assertEquals(expected.getAlcoholMin(), actual.getAlcoholMin());
        Assert.assertEquals(expected.getAlcoholMax(), actual.getAlcoholMax());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
        Assert.assertEquals(expected.getProfile(), actual.getProfile());
        Assert.assertEquals(expected.getIngredients(), actual.getIngredients());
        Assert.assertEquals(expected.getExamples(), actual.getExamples());
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
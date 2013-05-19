package test.blh.core.beerxml.types.builders;

import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.units.*;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.Style.TYPE;
import blh.core.beerxml.types.builders.*;
import blh.core.units.alcohol.ABV;
import blh.core.units.bitterness.IBU;
import blh.core.units.color.SRM;
import blh.core.units.gravity.SpecificGravity;

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
            builder.set(tag.getKey(), tag.getValue());
        }
        Style actual = builder.create();
        Style expected = new Style(name, category, categoryNumber, styleLetter, styleGuide, type, originalGravityMin, originalGravityMax, finalGravityMin, finalGravityMax, IBUMin, IBUMax, colorMin, colorMax, carbonationMin, carbonationMax, alcoholMin, alcoholMax, notes, profile, ingredients, examples);
        assertEquality(expected, actual);
    }

    public void assertEquality(Style expected, Style actual) {
        Assert.assertEquals(expected.name, actual.name);
        Assert.assertEquals(expected.category, actual.category);
        Assert.assertEquals(expected.categoryNumber, actual.categoryNumber);
        Assert.assertEquals(expected.styleLetter, actual.styleLetter);
        Assert.assertEquals(expected.styleGuide, actual.styleGuide);
        Assert.assertEquals(expected.type, actual.type);
        Assert.assertEquals(expected.originalGravityMin, actual.originalGravityMin);
        Assert.assertEquals(expected.originalGravityMax, actual.originalGravityMax);
        Assert.assertEquals(expected.finalGravityMin, actual.finalGravityMin);
        Assert.assertEquals(expected.finalGravityMax, actual.finalGravityMax);
        Assert.assertEquals(expected.IBUMin, actual.IBUMin);
        Assert.assertEquals(expected.IBUMax, actual.IBUMax);
        Assert.assertEquals(expected.colorMin, actual.colorMin);
        Assert.assertEquals(expected.colorMax, actual.colorMax);
        Assert.assertEquals(expected.carbonationMin, actual.carbonationMin);
        Assert.assertEquals(expected.carbonationMax, actual.carbonationMax);
        Assert.assertEquals(expected.alcoholMin, actual.alcoholMin);
        Assert.assertEquals(expected.alcoholMax, actual.alcoholMax);
        Assert.assertEquals(expected.notes, actual.notes);
        Assert.assertEquals(expected.profile, actual.profile);
        Assert.assertEquals(expected.ingredients, actual.ingredients);
        Assert.assertEquals(expected.examples, actual.examples);
    }
}
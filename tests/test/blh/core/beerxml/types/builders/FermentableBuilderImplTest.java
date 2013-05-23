package test.blh.core.beerxml.types.builders;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.UnknownTagException;
import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.GrainOrAdjunctFermentable;
import blh.core.beerxml.types.LiquidFermentable;
import blh.core.beerxml.types.builders.Builder;
import blh.core.beerxml.types.builders.FermentableBuilderImpl;
import blh.core.beerxml.types.builders.YeastBuilderImpl;
import blh.core.units.Lintner;
import blh.core.units.Percentage;
import blh.core.units.color.Lovibond;
import blh.core.units.color.SRM;
import blh.core.units.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik Larkö at 5/16/13:10:47 PM
 */
public class FermentableBuilderImplTest {

    @Test
    public void testAdjunctFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.FERMENTABLE_TYPE.ADJUNCT);
        assertEquality((GrainOrAdjunctFermentable) p.fst, (GrainOrAdjunctFermentable) p.snd);
    }

    @Test
    public void testGrainFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.FERMENTABLE_TYPE.GRAIN);
        assertEquality((GrainOrAdjunctFermentable) p.fst, (GrainOrAdjunctFermentable) p.snd);
    }

    @Test
    public void testDryExtractFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.FERMENTABLE_TYPE.DRY_EXTRACT);
    }

    @Test
    public void testExtractFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.FERMENTABLE_TYPE.EXTRACT);
        assertEquality((LiquidFermentable) p.fst, (LiquidFermentable) p.snd);
    }

    @Test
    public void testSugarFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.FERMENTABLE_TYPE.SUGAR);
    }

    private Pair<Fermentable, Fermentable> troll(Fermentable.FERMENTABLE_TYPE type) {
        String name = "a";
        Kilograms amount = new Kilograms(1);
        Percentage yield = new Percentage(2);
        String colorString = "3";
        boolean addAfterBoil = true;
        String origin = "b";
        String supplier = "c";
        String notes = "d";
        Percentage maxInBatch = new Percentage(4);
        double IBUGallonsPerPound = 5;
        Percentage coarseFineDiff = new Percentage(6);
        Percentage moisture = new Percentage(7);
        Lintner diastaticPower = new Lintner(8);
        Percentage protein = new Percentage(9);
        boolean recommendMash = true;

        Map<String, String> tags = new HashMap<>();
        BuilderUtils.addTag(tags, Fermentable.NAME, name);
        BuilderUtils.addTag(tags, Fermentable.TYPE, type.toString());
        BuilderUtils.addTag(tags, Fermentable.AMOUNT, amount);
        BuilderUtils.addTag(tags, Fermentable.YIELD, yield);
        BuilderUtils.addTag(tags, Fermentable.COLOR, colorString);
        BuilderUtils.addTag(tags, Fermentable.ADD_AFTER_BOIL, addAfterBoil);
        BuilderUtils.addTag(tags, Fermentable.ORIGIN, origin);
        BuilderUtils.addTag(tags, Fermentable.SUPPLIER, supplier);
        BuilderUtils.addTag(tags, Fermentable.NOTES, notes);
        BuilderUtils.addTag(tags, GrainOrAdjunctFermentable.COARSE_FINE_DIFF, coarseFineDiff);
        BuilderUtils.addTag(tags, GrainOrAdjunctFermentable.MOISTURE, moisture);
        BuilderUtils.addTag(tags, GrainOrAdjunctFermentable.DIASTATIC_POWER, diastaticPower);
        BuilderUtils.addTag(tags, GrainOrAdjunctFermentable.PROTEIN, protein);
        BuilderUtils.addTag(tags, Fermentable.MAX_IN_BATCH, maxInBatch);
        BuilderUtils.addTag(tags, GrainOrAdjunctFermentable.RECOMMEND_MASH, recommendMash);
        BuilderUtils.addTag(tags, LiquidFermentable.IBU_GALLONS_PER_POUND, IBUGallonsPerPound);

        FermentableBuilderImpl builder = new FermentableBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }

        Fermentable expected;
        if (type == Fermentable.FERMENTABLE_TYPE.ADJUNCT || type == Fermentable.FERMENTABLE_TYPE.GRAIN) {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            expected = new GrainOrAdjunctFermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch, coarseFineDiff, moisture, diastaticPower, protein, recommendMash);
        } else if (type == Fermentable.FERMENTABLE_TYPE.EXTRACT) {
            SRM color = new SRM(Double.parseDouble(colorString));
            expected = new LiquidFermentable(name, amount, yield, addAfterBoil, origin, supplier, notes, maxInBatch, color, IBUGallonsPerPound);
        } else {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            expected = new Fermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);
        }
        Fermentable actual = builder.create();
        assertEquality(expected, actual);

        return new Pair<>(expected, actual);
    }

    private void assertEquality(Fermentable expected, Fermentable actual) {
        Assert.assertEquals(expected.name, actual.name);
        Assert.assertEquals(expected.type, actual.type);
        Assert.assertEquals(expected.amount, actual.amount);
        Assert.assertEquals(expected.yield, actual.yield);
        Assert.assertEquals(expected.color, actual.color);
        Assert.assertEquals(expected.addAfterBoil, actual.addAfterBoil);
        Assert.assertEquals(expected.origin, actual.origin);
        Assert.assertEquals(expected.supplier, actual.supplier);
        Assert.assertEquals(expected.notes, actual.notes);
        Assert.assertEquals(expected.maxInBatch, actual.maxInBatch);
    }

    private void assertEquality(GrainOrAdjunctFermentable expected, GrainOrAdjunctFermentable actual) {
        Assert.assertEquals(expected.coarseFineDiff, actual.coarseFineDiff);
        Assert.assertEquals(expected.moisture, actual.moisture);
        Assert.assertEquals(expected.diastaticPower, actual.diastaticPower);
        Assert.assertEquals(expected.protein, actual.protein);
        Assert.assertEquals(expected.recommendMash, actual.recommendMash);
    }

    private void assertEquality(LiquidFermentable expected, LiquidFermentable actual) {
        Assert.assertEquals(expected.IBUGallonsPerPound, actual.IBUGallonsPerPound, 0.0001);
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

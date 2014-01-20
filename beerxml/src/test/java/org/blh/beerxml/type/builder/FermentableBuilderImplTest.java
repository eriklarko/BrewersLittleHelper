package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.GrainOrAdjunctFermentable;
import org.blh.beerxml.type.LiquidFermentable;
import org.blh.core.unit.Lintner;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.color.SRM;
import org.blh.core.unit.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
        Assert.assertEquals(expected.getYield(), actual.getYield());
        Assert.assertEquals(expected.getColor(), actual.getColor());
        Assert.assertEquals(expected.isAddAfterBoil(), actual.isAddAfterBoil());
        Assert.assertEquals(expected.getOrigin(), actual.getOrigin());
        Assert.assertEquals(expected.getSupplier(), actual.getSupplier());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
        Assert.assertEquals(expected.getMaxInBatch(), actual.getMaxInBatch());
    }

    private void assertEquality(GrainOrAdjunctFermentable expected, GrainOrAdjunctFermentable actual) {
        Assert.assertEquals(expected.getCoarseFineDiff(), actual.getCoarseFineDiff());
        Assert.assertEquals(expected.getMoisture(), actual.getMoisture());
        Assert.assertEquals(expected.getDiastaticPower(), actual.getDiastaticPower());
        Assert.assertEquals(expected.getProtein(), actual.getProtein());
        Assert.assertEquals(expected.recommendsMash(), actual.recommendsMash());
    }

    private void assertEquality(LiquidFermentable expected, LiquidFermentable actual) {
        Assert.assertEquals(expected.getIBUGallonsPerPound(), actual.getIBUGallonsPerPound(), 0.0001);
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

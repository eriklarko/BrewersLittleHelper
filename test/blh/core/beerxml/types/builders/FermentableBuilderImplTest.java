package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.GrainOrAdjunctFermentable;
import blh.core.beerxml.types.LiquidFermentable;
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
        Pair<Fermentable, Fermentable> p = troll(Fermentable.TYPE.ADJUNCT);
        assertEquality((GrainOrAdjunctFermentable) p.fst, (GrainOrAdjunctFermentable) p.snd);
    }

    @Test
    public void testGrainFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.TYPE.GRAIN);
        assertEquality((GrainOrAdjunctFermentable) p.fst, (GrainOrAdjunctFermentable) p.snd);
    }

    @Test
    public void testDryExtractFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.TYPE.DRY_EXTRACT);
    }

    @Test
    public void testExtractFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.TYPE.EXTRACT);
        assertEquality((LiquidFermentable) p.fst, (LiquidFermentable) p.snd);
    }

    @Test
    public void testSugarFermentable() throws Exception {
        Pair<Fermentable, Fermentable> p = troll(Fermentable.TYPE.SUGAR);
    }

    private Pair<Fermentable, Fermentable> troll(Fermentable.TYPE type) {
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
        BuilderUtils.addTag(tags, "name", name);
        BuilderUtils.addTag(tags, "type", type.toString());
        BuilderUtils.addTag(tags, "amount", amount);
        BuilderUtils.addTag(tags, "yield", yield);
        BuilderUtils.addTag(tags, "color", colorString);
        BuilderUtils.addTag(tags, "add_after_boil", addAfterBoil);
        BuilderUtils.addTag(tags, "origin", origin);
        BuilderUtils.addTag(tags, "supplier", supplier);
        BuilderUtils.addTag(tags, "notes", notes);
        BuilderUtils.addTag(tags, "coarse_fine_diff", coarseFineDiff);
        BuilderUtils.addTag(tags, "moisture", moisture);
        BuilderUtils.addTag(tags, "diastatic_power", diastaticPower);
        BuilderUtils.addTag(tags, "protein", protein);
        BuilderUtils.addTag(tags, "max_in_batch", maxInBatch);
        BuilderUtils.addTag(tags, "recommended_mash", recommendMash);
        BuilderUtils.addTag(tags, "ibu_gal_per_lb", IBUGallonsPerPound);

        FermentableBuilderImpl builder = new FermentableBuilderImpl();
        for(Map.Entry<String, String> tag : tags.entrySet()) {
            builder.set(tag.getKey(), tag.getValue());
        }

        Fermentable expected;
        if(type ==  Fermentable.TYPE.ADJUNCT || type ==  Fermentable.TYPE.GRAIN)  {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            expected = new GrainOrAdjunctFermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch, coarseFineDiff, moisture, diastaticPower, protein, recommendMash);
        } else if(type ==  Fermentable.TYPE.EXTRACT) {
            SRM color = new SRM(Double.parseDouble(colorString));
            expected = new LiquidFermentable(name, amount, yield, addAfterBoil, origin, supplier, notes, maxInBatch, color, IBUGallonsPerPound);
        } else {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            expected = new Fermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);
        }
        Fermentable actual = builder.create();
        assertEquality(expected, actual);

        return new Pair<Fermentable, Fermentable>(expected, actual);
    }
    
    private void assertEquality(Fermentable expected, Fermentable actual) {
        Assert.assertEquals(expected.name , actual.name) ;
        Assert.assertEquals(expected.type , actual.type) ;
        Assert.assertEquals(expected.amount , actual.amount);
        Assert.assertEquals(expected.yield , actual.yield);
        Assert.assertEquals(expected.color, actual.color);
        Assert.assertEquals(expected.addAfterBoil , actual.addAfterBoil);
        Assert.assertEquals(expected.origin , actual.origin);
        Assert.assertEquals(expected.supplier , actual.supplier);
        Assert.assertEquals(expected.notes , actual.notes);
        Assert.assertEquals(expected.maxInBatch , actual.maxInBatch);
    }

    private void assertEquality(GrainOrAdjunctFermentable expected, GrainOrAdjunctFermentable actual) {
        Assert.assertEquals(expected.coarseFineDiff , actual.coarseFineDiff);
        Assert.assertEquals(expected.moisture , actual.moisture) ;
        Assert.assertEquals(expected.diastaticPower , actual.diastaticPower);
        Assert.assertEquals(expected.protein , actual.protein);
        Assert.assertEquals(expected.recommendMash , actual.recommendMash);
    }

    private void assertEquality(LiquidFermentable expected, LiquidFermentable actual) {
        Assert.assertEquals(expected.IBUGallonsPerPound , actual.IBUGallonsPerPound, 0.0001);
    }
}

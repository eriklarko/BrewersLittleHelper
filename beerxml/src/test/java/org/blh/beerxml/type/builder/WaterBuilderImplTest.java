package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Water;
import org.blh.core.unit.PH;
import org.blh.core.unit.PPM;
import org.blh.core.unit.volume.Liters;
import org.junit.Assert;
import org.junit.Test;

public class WaterBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        Liters amount = new Liters(2);
        PPM calcium = new PPM(3);
        PPM bicarbonate = new PPM(4);
        PPM sulfate = new PPM(5);
        PPM chloride = new PPM(6);
        PPM sodium = new PPM(7);
        PPM magnesium = new PPM(8);
        PH ph = new PH(9);
        String notes = "10";

        BuilderUtils.addTag(tags, Water.NAME, name);
        BuilderUtils.addTag(tags, Water.AMOUNT, amount);
        BuilderUtils.addTag(tags, Water.CALCIUM, calcium);
        BuilderUtils.addTag(tags, Water.BICARBONATE, bicarbonate);
        BuilderUtils.addTag(tags, Water.SULFATE, sulfate);
        BuilderUtils.addTag(tags, Water.CHLORIDE, chloride);
        BuilderUtils.addTag(tags, Water.SODIUM, sodium);
        BuilderUtils.addTag(tags, Water.MAGNESIUM, magnesium);
        BuilderUtils.addTag(tags, Water.PH, ph);
        BuilderUtils.addTag(tags, Water.NOTES, notes);
        WaterBuilderImpl builder = new WaterBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Water actual = builder.create();
        Water expected = new Water(name, amount, calcium, bicarbonate, sulfate, chloride, sodium, magnesium, ph, notes);
        assertEquality(expected, actual);
    }

    private void assertEquality(Water expected, Water actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
        Assert.assertEquals(expected.getCalcium(), actual.getCalcium());
        Assert.assertEquals(expected.getBicarbonate(), actual.getBicarbonate());
        Assert.assertEquals(expected.getSulfate(), actual.getSulfate());
        Assert.assertEquals(expected.getChloride(), actual.getChloride());
        Assert.assertEquals(expected.getSodium(), actual.getSodium());
        Assert.assertEquals(expected.getMagnesium(), actual.getMagnesium());
        Assert.assertEquals(expected.getPH(), actual.getPH());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
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
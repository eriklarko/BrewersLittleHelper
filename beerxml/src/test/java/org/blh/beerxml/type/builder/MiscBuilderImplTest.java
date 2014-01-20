package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Misc.MISC_TYPE;
import org.blh.beerxml.type.Misc.MISC_USE;
import org.blh.core.unit.time.Minutes;
import org.junit.Assert;
import org.junit.Test;

public class MiscBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        MISC_TYPE type = MISC_TYPE.AGENT;
        MISC_USE use = MISC_USE.BOIL;
        Minutes time = new Minutes(4);
        double amount = 5;
        boolean amountIsWeight = true;
        String useFor = "7";
        String notes = "8";

        BuilderUtils.addTag(tags, Misc.NAME, name);
        BuilderUtils.addTag(tags, Misc.TYPE, type.toString());
        BuilderUtils.addTag(tags, Misc.USE, use.toString());
        BuilderUtils.addTag(tags, Misc.TIME, time);
        BuilderUtils.addTag(tags, Misc.AMOUNT, amount);
        BuilderUtils.addTag(tags, Misc.AMOUNT_IS_WEIGHT, amountIsWeight);
        BuilderUtils.addTag(tags, Misc.USE_FOR, useFor);
        BuilderUtils.addTag(tags, Misc.NOTES, notes);
        MiscBuilderImpl builder = new MiscBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Misc actual = builder.create();
        Misc expected = new Misc(name, type, use, time, amount, amountIsWeight, useFor, notes);
        assertEquality(expected, actual);
    }

    private void assertEquality(Misc expected, Misc actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getUse(), actual.getUse());
        Assert.assertEquals(expected.getTime(), actual.getTime());
        Assert.assertEquals(expected.getAmount(), actual.getAmount(), 0.00001);
        Assert.assertEquals(expected.amountIsWeight(), actual.amountIsWeight());
        Assert.assertEquals(expected.getUseFor(), actual.getUseFor());
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
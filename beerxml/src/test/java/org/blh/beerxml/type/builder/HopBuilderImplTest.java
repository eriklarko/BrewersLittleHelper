package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.Hop.HOP_FORM;
import org.blh.beerxml.type.Hop.HOP_TYPE;
import org.blh.beerxml.type.Hop.HOP_USE;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

public class HopBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        Percentage alpha = new Percentage(2);
        Kilograms amount = new Kilograms(3);
        HOP_USE use = HOP_USE.AROMA;
        Minutes time = new Minutes(5);
        String notes = "6";
        HOP_TYPE type = HOP_TYPE.BITTERING;
        HOP_FORM form = HOP_FORM.LEAF;
        Percentage beta = new Percentage(9);
        Percentage hopStabilityIndex = new Percentage(10);
        String origin = "11";
        String substitutes = "12";
        Percentage humulene = new Percentage(13);
        Percentage caryophyllene = new Percentage(14);
        Percentage cohumulone = new Percentage(15);
        Percentage myrcene = new Percentage(16);

        BuilderUtils.addTag(tags, Hop.NAME, name);
        BuilderUtils.addTag(tags, Hop.ALPHA, alpha);
        BuilderUtils.addTag(tags, Hop.AMOUNT, amount);
        BuilderUtils.addTag(tags, Hop.USE, use.toString());
        BuilderUtils.addTag(tags, Hop.TIME, time);
        BuilderUtils.addTag(tags, Hop.NOTES, notes);
        BuilderUtils.addTag(tags, Hop.TYPE, type.toString());
        BuilderUtils.addTag(tags, Hop.FORM, form.toString());
        BuilderUtils.addTag(tags, Hop.BETA, beta);
        BuilderUtils.addTag(tags, Hop.HOP_STABILITY_INDEX, hopStabilityIndex);
        BuilderUtils.addTag(tags, Hop.ORIGIN, origin);
        BuilderUtils.addTag(tags, Hop.SUBSTITUTES, substitutes);
        BuilderUtils.addTag(tags, Hop.HUMULENE, humulene);
        BuilderUtils.addTag(tags, Hop.CARYOPHYLLENE, caryophyllene);
        BuilderUtils.addTag(tags, Hop.COHUMULONE, cohumulone);
        BuilderUtils.addTag(tags, Hop.MYRCENE, myrcene);
        HopBuilderImpl builder = new HopBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Hop actual = builder.create();
        Hop expected = new Hop(name, alpha, amount, use, time, notes, type, form, beta, hopStabilityIndex, origin, substitutes, humulene, caryophyllene, cohumulone, myrcene);
        assertEquality(expected, actual);
    }

    private void assertEquality(Hop expected, Hop actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getAlpha(), actual.getAlpha());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
        Assert.assertEquals(expected.getUse(), actual.getUse());
        Assert.assertEquals(expected.getTime(), actual.getTime());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getForm(), actual.getForm());
        Assert.assertEquals(expected.getBeta(), actual.getBeta());
        Assert.assertEquals(expected.getHopStabilityIndex(), actual.getHopStabilityIndex());
        Assert.assertEquals(expected.getOrigin(), actual.getOrigin());
        Assert.assertEquals(expected.getSubstitutes(), actual.getSubstitutes());
        Assert.assertEquals(expected.getHumulene(), actual.getHumulene());
        Assert.assertEquals(expected.getCaryophyllene(), actual.getCaryophyllene());
        Assert.assertEquals(expected.getCohumulone(), actual.getCohumulone());
        Assert.assertEquals(expected.getMyrcene(), actual.getMyrcene());
    }

    @Test
    public void testSetUnknownTag() {
        try {
            Builder builder = new HopBuilderImpl();
            builder.set("ökldafs", "");
            Assert.fail();
        } catch(UnknownTagException ex) {
            Assert.assertTrue(true);
        } catch(ParseException ex) {
            Assert.fail();
        }
    }
}
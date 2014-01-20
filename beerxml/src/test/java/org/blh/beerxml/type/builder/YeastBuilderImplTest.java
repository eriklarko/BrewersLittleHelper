package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Yeast;
import org.blh.beerxml.type.Yeast.YEAST_FLOCCULATION;
import org.blh.beerxml.type.Yeast.YEAST_FORM;
import org.blh.beerxml.type.Yeast.YEAST_TYPE;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.temperature.Celsius;
import org.junit.Assert;
import org.junit.Test;

public class YeastBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        YEAST_TYPE type = YEAST_TYPE.ALE;
        YEAST_FORM form = YEAST_FORM.CULTURE;
        double amount = 4;
        boolean amountIsWeight = true;
        String laboratory = "6";
        String productId = "7";
        Celsius minTemperature = new Celsius(8);
        Celsius maxTemperature = new Celsius(9);
        YEAST_FLOCCULATION flocculation = YEAST_FLOCCULATION.HIGH;
        Percentage attenuation = new Percentage(11);
        String notes = "12";
        String bestFor = "13";
        int timesCultured = 14;
        int maxReuse = 15;
        boolean addToSecondary = true;

        BuilderUtils.addTag(tags, Yeast.NAME, name);
        BuilderUtils.addTag(tags, Yeast.TYPE, type.toString());
        BuilderUtils.addTag(tags, Yeast.FORM, form.toString());
        BuilderUtils.addTag(tags, Yeast.AMOUNT, amount);
        BuilderUtils.addTag(tags, Yeast.AMOUNT_IS_WEIGHT, amountIsWeight);
        BuilderUtils.addTag(tags, Yeast.LABORATORY, laboratory);
        BuilderUtils.addTag(tags, Yeast.PRODUCT_ID, productId);
        BuilderUtils.addTag(tags, Yeast.MIN_TEMPERATURE, minTemperature);
        BuilderUtils.addTag(tags, Yeast.MAX_TEMPERATURE, maxTemperature);
        BuilderUtils.addTag(tags, Yeast.FLOCCULATION, flocculation.toString());
        BuilderUtils.addTag(tags, Yeast.ATTENUATION, attenuation);
        BuilderUtils.addTag(tags, Yeast.NOTES, notes);
        BuilderUtils.addTag(tags, Yeast.BEST_FOR, bestFor);
        BuilderUtils.addTag(tags, Yeast.TIMES_CULTURED, timesCultured);
        BuilderUtils.addTag(tags, Yeast.MAX_REUSE, maxReuse);
        BuilderUtils.addTag(tags, Yeast.ADD_TO_SECONDARY, addToSecondary);
        YeastBuilderImpl builder = new YeastBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Yeast actual = builder.create();
        Yeast expected = new Yeast(name, type, form, amount, amountIsWeight, laboratory, productId, minTemperature, maxTemperature, flocculation, attenuation, notes, bestFor, timesCultured, maxReuse, addToSecondary);
        assertEquality(expected, actual);
    }

    private void assertEquality(Yeast expected, Yeast actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getForm(), actual.getForm());
        Assert.assertEquals(expected.getAmount(), actual.getAmount(), 0.00001);
        Assert.assertEquals(expected.amountIsWeight(), actual.amountIsWeight());
        Assert.assertEquals(expected.getLaboratory(), actual.getLaboratory());
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getMinTemperature(), actual.getMinTemperature());
        Assert.assertEquals(expected.getMaxTemperature(), actual.getMaxTemperature());
        Assert.assertEquals(expected.getFlocculation(), actual.getFlocculation());
        Assert.assertEquals(expected.getAttenuation(), actual.getAttenuation());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
        Assert.assertEquals(expected.getBestFor(), actual.getBestFor());
        Assert.assertEquals(expected.getTimesCultured(), actual.getTimesCultured());
        Assert.assertEquals(expected.getMaxReuse(), actual.getMaxReuse());
        Assert.assertEquals(expected.addToSecondary(), actual.addToSecondary());
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
package test.blh.core.beerxml.types.builders;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.UnknownTagException;
import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.units.*;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.Yeast.YEAST_FLOCCULATION;
import blh.core.beerxml.types.Yeast.YEAST_FORM;
import blh.core.beerxml.types.Yeast.YEAST_TYPE;
import blh.core.beerxml.types.builders.*;
import blh.core.units.temperature.Celcius;

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
        Celcius minTemperature = new Celcius(8);
        Celcius maxTemperature = new Celcius(9);
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
        Assert.assertEquals(expected.name, actual.name);
        Assert.assertEquals(expected.type, actual.type);
        Assert.assertEquals(expected.form, actual.form);
        Assert.assertEquals(expected.amount, actual.amount, 0.00001);
        Assert.assertEquals(expected.amountIsWeight, actual.amountIsWeight);
        Assert.assertEquals(expected.laboratory, actual.laboratory);
        Assert.assertEquals(expected.productId, actual.productId);
        Assert.assertEquals(expected.minTemperature, actual.minTemperature);
        Assert.assertEquals(expected.maxTemperature, actual.maxTemperature);
        Assert.assertEquals(expected.flocculation, actual.flocculation);
        Assert.assertEquals(expected.attenuation, actual.attenuation);
        Assert.assertEquals(expected.notes, actual.notes);
        Assert.assertEquals(expected.bestFor, actual.bestFor);
        Assert.assertEquals(expected.timesCultured, actual.timesCultured);
        Assert.assertEquals(expected.maxReuse, actual.maxReuse);
        Assert.assertEquals(expected.addToSecondary, actual.addToSecondary);
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
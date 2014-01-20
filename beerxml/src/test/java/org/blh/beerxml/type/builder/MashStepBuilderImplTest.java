package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.MashStep.MASH_STEP_TYPE;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.junit.Assert;
import org.junit.Test;

public class MashStepBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        MASH_STEP_TYPE type = MASH_STEP_TYPE.DECOCTION;
        Liters infuseAmount = new Liters(3);
        Celsius stepTemp = new Celsius(4);
        Minutes stepTime = new Minutes(5);
        Minutes rampTime = new Minutes(6);
        Celsius endTemp = new Celsius(7);

        BuilderUtils.addTag(tags, MashStep.NAME, name);
        BuilderUtils.addTag(tags, MashStep.TYPE, type.toString());
        BuilderUtils.addTag(tags, MashStep.INFUSE_AMOUNT, infuseAmount);
        BuilderUtils.addTag(tags, MashStep.STEP_TEMP, stepTemp);
        BuilderUtils.addTag(tags, MashStep.STEP_TIME, stepTime);
        BuilderUtils.addTag(tags, MashStep.RAMP_TIME, rampTime);
        BuilderUtils.addTag(tags, MashStep.END_TEMP, endTemp);
        MashStepBuilderImpl builder = new MashStepBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        MashStep actual = builder.create();
        MashStep expected = new MashStep(name, type, infuseAmount, stepTemp, stepTime, rampTime, endTemp);
        assertEquality(expected, actual);
    }

    private void assertEquality(MashStep expected, MashStep actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getInfuseAmount(), actual.getInfuseAmount());
        Assert.assertEquals(expected.getStepTemp(), actual.getStepTemp());
        Assert.assertEquals(expected.getStepTime(), actual.getStepTime());
        Assert.assertEquals(expected.getRampTime(), actual.getRampTime());
        Assert.assertEquals(expected.getEndTemp(), actual.getEndTemp());
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
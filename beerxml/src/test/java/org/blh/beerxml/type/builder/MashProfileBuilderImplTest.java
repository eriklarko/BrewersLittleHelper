package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.MashProfile;
import org.blh.core.unit.PH;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

public class MashProfileBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        Celsius grainTemperature = new Celsius(2);
        String notes = "4";
        Celsius tunTemperature = new Celsius(5);
        Celsius spargeTemperature = new Celsius(6);
        PH spargePH = new PH(7);
        Kilograms tunWeight = new Kilograms(8);
        double tunSpecificHeat = 9;
        boolean adjustForEquipmentTemperature = true;

        BuilderUtils.addTag(tags, MashProfile.NAME, name);
        BuilderUtils.addTag(tags, MashProfile.GRAIN_TEMPERATURE, grainTemperature);
        BuilderUtils.addTag(tags, MashProfile.NOTES, notes);
        BuilderUtils.addTag(tags, MashProfile.TUN_TEMPERATURE, tunTemperature);
        BuilderUtils.addTag(tags, MashProfile.SPARGE_TEMPERATURE, spargeTemperature);
        BuilderUtils.addTag(tags, MashProfile.SPARGE_PH, spargePH);
        BuilderUtils.addTag(tags, MashProfile.TUN_WEIGHT, tunWeight);
        BuilderUtils.addTag(tags, MashProfile.TUN_SPECIFIC_HEAT, tunSpecificHeat);
        BuilderUtils.addTag(tags, MashProfile.ADJUST_FOR_EQUIPMENT_TEMPERATURE, adjustForEquipmentTemperature);
        MashProfileBuilderImpl builder = new MashProfileBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        MashProfile actual = builder.create();
        MashProfile expected = new MashProfile(name, grainTemperature, null, notes, tunTemperature, spargeTemperature, spargePH, tunWeight, tunSpecificHeat, adjustForEquipmentTemperature);
        assertEquality(expected, actual);
    }

    private void assertEquality(MashProfile expected, MashProfile actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getGrainTemperature(), actual.getGrainTemperature());
        Assert.assertEquals(expected.getMashSteps(), actual.getMashSteps());
        Assert.assertEquals(expected.getNotes(), actual.getNotes());
        Assert.assertEquals(expected.getTunTemperature(), actual.getTunTemperature());
        Assert.assertEquals(expected.getSpargeTemperature(), actual.getSpargeTemperature());
        Assert.assertEquals(expected.getSpargePH(), actual.getSpargePH());
        Assert.assertEquals(expected.getTunWeight(), actual.getTunWeight());
        Assert.assertEquals(expected.getTunSpecificHeat(), actual.getTunSpecificHeat(), 0.00001);
        Assert.assertEquals(expected.adjustForEquipmentTemperature(), actual.adjustForEquipmentTemperature());
    }

    @Test
    public void testSetUnknownTag() {
        try {
            Builder builder = new MashProfileBuilderImpl();
            builder.set("ökldafs", "");
            Assert.fail();
        } catch(UnknownTagException ex) {
            Assert.assertTrue(true);
        } catch(ParseException ex) {
            Assert.fail();
        }
    }
}
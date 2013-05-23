package test.blh.core.beerxml.types.builders;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.UnknownTagException;
import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.units.*;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.builders.*;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

public class EquipmentBuilderImplTest {

    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "1";
        Liters boilSize = new Liters(2);
        Liters batchSize = new Liters(3);
        Liters tunVolume = new Liters(4);
        Kilograms tunWeight = new Kilograms(5);
        double tunSpecificHeat = 6;
        Liters topUpWater = new Liters(7);
        Liters trubChillerLoss = new Liters(8);
        Percentage evapRate = new Percentage(9);
        Minutes boilTime = new Minutes(10);
        boolean calculateBoilVolume = true;
        Liters lauterDeadSpace = new Liters(12);
        Liters topUpKettle = new Liters(13);
        Percentage hopUtilization = new Percentage(14);
        String notes = "15";

        BuilderUtils.addTag(tags, Equipment.NAME, name);
        BuilderUtils.addTag(tags, Equipment.BOIL_SIZE, boilSize);
        BuilderUtils.addTag(tags, Equipment.BATCH_SIZE, batchSize);
        BuilderUtils.addTag(tags, Equipment.TUN_VOLUME, tunVolume);
        BuilderUtils.addTag(tags, Equipment.TUN_WEIGHT, tunWeight);
        BuilderUtils.addTag(tags, Equipment.TUN_SPECIFIC_HEAT, tunSpecificHeat);
        BuilderUtils.addTag(tags, Equipment.TOP_UP_WATER, topUpWater);
        BuilderUtils.addTag(tags, Equipment.TRUB_CHILLER_LOSS, trubChillerLoss);
        BuilderUtils.addTag(tags, Equipment.EVAP_RATE, evapRate);
        BuilderUtils.addTag(tags, Equipment.BOIL_TIME, boilTime);
        BuilderUtils.addTag(tags, Equipment.CALCULATE_BOIL_VOLUME, calculateBoilVolume);
        BuilderUtils.addTag(tags, Equipment.LAUTER_DEAD_SPACE, lauterDeadSpace);
        BuilderUtils.addTag(tags, Equipment.TOP_UP_KETTLE, topUpKettle);
        BuilderUtils.addTag(tags, Equipment.HOP_UTILIZATION, hopUtilization);
        BuilderUtils.addTag(tags, Equipment.NOTES, notes);
        EquipmentBuilderImpl builder = new EquipmentBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            try {
                builder.set(tag.getKey(), tag.getValue());
            } catch (UnknownTagException ex) {
                ex.printStackTrace();
                Assert.fail();
            }
        }
        Equipment actual = builder.create();
        Equipment expected = new Equipment(name, boilSize, batchSize, tunVolume, tunWeight, tunSpecificHeat, topUpWater, trubChillerLoss, evapRate, boilTime, calculateBoilVolume, lauterDeadSpace, topUpKettle, hopUtilization, notes);
        assertEquality(expected, actual);
    }

    private void assertEquality(Equipment expected, Equipment actual) {
        Assert.assertEquals(expected.name, actual.name);
        Assert.assertEquals(expected.boilSize, actual.boilSize);
        Assert.assertEquals(expected.batchSize, actual.batchSize);
        Assert.assertEquals(expected.tunVolume, actual.tunVolume);
        Assert.assertEquals(expected.tunWeight, actual.tunWeight);
        Assert.assertEquals(expected.tunSpecificHeat, actual.tunSpecificHeat, 0.00001);
        Assert.assertEquals(expected.topUpWater, actual.topUpWater);
        Assert.assertEquals(expected.trubChillerLoss, actual.trubChillerLoss);
        Assert.assertEquals(expected.evapRate, actual.evapRate);
        Assert.assertEquals(expected.boilTime, actual.boilTime);
        Assert.assertEquals(expected.calculateBoilVolume, actual.calculateBoilVolume);
        Assert.assertEquals(expected.lauterDeadSpace, actual.lauterDeadSpace);
        Assert.assertEquals(expected.topUpKettle, actual.topUpKettle);
        Assert.assertEquals(expected.hopUtilization, actual.hopUtilization);
        Assert.assertEquals(expected.notes, actual.notes);
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
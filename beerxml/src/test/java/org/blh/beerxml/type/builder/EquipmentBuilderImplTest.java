package org.blh.beerxml.type.builder;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.ParseException; 
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.Equipment;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

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
                Assert.fail(ex.getMessage());
            }
        }
        Equipment actual = builder.create();
        Equipment expected = new Equipment(name, boilSize, batchSize, tunVolume, tunWeight, tunSpecificHeat, topUpWater, trubChillerLoss, evapRate, boilTime, calculateBoilVolume, lauterDeadSpace, topUpKettle, hopUtilization, notes);
        assertEquality(expected, actual);
    }

    private void assertEquality(Equipment expected, Equipment actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBoilSize(), actual.getBoilSize());
        Assert.assertEquals(expected.getBatchSize(), actual.getBatchSize());
        Assert.assertEquals(expected.getTunVolume(), actual.getTunVolume());
        Assert.assertEquals(expected.getTunWeight(), actual.getTunWeight());
        Assert.assertEquals(expected.getTunSpecificHeat(), actual.getTunSpecificHeat(), 0.00001);
        Assert.assertEquals(expected.getTopUpWater(), actual.getTopUpWater());
        Assert.assertEquals(expected.getTrubChillerLoss(), actual.getTrubChillerLoss());
        Assert.assertEquals(expected.getEvapRate(), actual.getEvapRate());
        Assert.assertEquals(expected.getBoilTime(), actual.getBoilTime());
        Assert.assertEquals(expected.isCalculateBoilVolume(), actual.isCalculateBoilVolume());
        Assert.assertEquals(expected.getLauterDeadSpace(), actual.getLauterDeadSpace());
        Assert.assertEquals(expected.getTopUpKettle(), actual.getTopUpKettle());
        Assert.assertEquals(expected.getHopUtilization(), actual.getHopUtilization());
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
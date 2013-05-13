/*
 * To change a template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.test.blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.builders.EquipmentBuilderImpl;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class EquipmentBuilderImplTest {

    public static void addTag(Map<String, String> map, String name, String value) {
        map.put(name, value);
    }

    public static void addTag(Map<String, String> map, String name, double value) {
        map.put(name, String.valueOf(value));
    }

    public static void addTag(Map<String, String> map, String name, int value) {
        map.put(name, String.valueOf(value));
    }

    public static void addTag(Map<String, String> map, String name, boolean value) {
        map.put(name, String.valueOf(value));
    }

    /**
     * Test of set method, of class EquipmentBuilderImpl.
     */
    @Test
    public void testSet() {
        Map<String, String> tags = new HashMap<>();

        String name = "a";
        Liters boilSize = new Liters(1);
        Liters batchSize = new Liters(2);
        Liters tunVolume = new Liters(3);
        Kilograms tunWeight = new Kilograms(4);
        double tunSpecificHeat = 5;
        Liters topUpWater = new Liters(6);
        Liters trubChillerLoss = new Liters(7);
        Percentage evapRate = new Percentage(8);
        Minutes boilTime = new Minutes(9);
        boolean calculateBoilVolume = true;
        Liters lauterDeadSpace = new Liters(10);
        Liters topUpKettle = new Liters(11);
        Percentage hopUtilization = new Percentage(12);
        String notes = "b";

        addTag(tags, "name", name);
        addTag(tags, "boil_size", boilSize.value());
        addTag(tags, "batch_size", batchSize.value());
        addTag(tags, "tun_volume", tunVolume.value());
        addTag(tags, "tun_weight", tunWeight.value());
        addTag(tags, "tun_specific_heat", tunSpecificHeat);
        addTag(tags, "top_up_water", topUpWater.value());
        addTag(tags, "trub_chiller_loss", trubChillerLoss.value());
        addTag(tags, "evap_rate", evapRate.value());
        addTag(tags, "boil_time", boilTime.value());
        addTag(tags, "calc_boil_volume", calculateBoilVolume);
        addTag(tags, "lauter_deadspace", lauterDeadSpace.value());
        addTag(tags, "top_up_kettle", topUpKettle.value());
        addTag(tags, "hop_utilization", hopUtilization.value());
        addTag(tags, "notes", notes);

        EquipmentBuilderImpl instance = new EquipmentBuilderImpl();
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            instance.set(tag.getKey(), tag.getValue());
        }
        Equipment result = instance.create();
        Equipment expected = new Equipment(name, boilSize, batchSize, tunVolume, tunWeight, tunSpecificHeat, topUpWater, trubChillerLoss, evapRate, boilTime, calculateBoilVolume, lauterDeadSpace, topUpKettle, hopUtilization, notes);
        assertEquals(expected, result);
    }

    public void assertEquals(Equipment a, Equipment b) {
        Assert.assertEquals(a.name, b.name);
        Assert.assertEquals(a.boilSize, b.boilSize);
        Assert.assertEquals(a.batchSize, b.batchSize);
        Assert.assertEquals(a.tunVolume, b.tunVolume);
        Assert.assertEquals(a.tunWeight, b.tunWeight);
        Assert.assertEquals(a.tunSpecificHeat, b.tunSpecificHeat, 0.001);
        Assert.assertEquals(a.topUpWater, b.topUpWater);
        Assert.assertEquals(a.trubChillerLoss, b.trubChillerLoss);
        Assert.assertEquals(a.evapRate, b.evapRate);
        Assert.assertEquals(a.boilTime, b.boilTime);
        Assert.assertEquals(a.calculateBoilVolume, b.calculateBoilVolume);
        Assert.assertEquals(a.lauterDeadSpace, b.lauterDeadSpace);
        Assert.assertEquals(a.topUpKettle, b.topUpKettle);
        Assert.assertEquals(a.hopUtilization, b.hopUtilization);
        Assert.assertEquals(a.notes, b.notes);
    }
}

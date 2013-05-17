package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.builders.EquipmentBuilderImpl;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinner
 */
public class EquipmentBuilderImplTest {

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

        BuilderUtils.addTag(tags, "name", name);
        BuilderUtils.addTag(tags, "boil_size", boilSize);
        BuilderUtils.addTag(tags, "batch_size", batchSize);
        BuilderUtils.addTag(tags, "tun_volume", tunVolume);
        BuilderUtils.addTag(tags, "tun_weight", tunWeight);
        BuilderUtils.addTag(tags, "tun_specific_heat", tunSpecificHeat);
        BuilderUtils.addTag(tags, "top_up_water", topUpWater);
        BuilderUtils.addTag(tags, "trub_chiller_loss", trubChillerLoss);
        BuilderUtils.addTag(tags, "evap_rate", evapRate);
        BuilderUtils.addTag(tags, "boil_time", boilTime);
        BuilderUtils.addTag(tags, "calc_boil_volume", calculateBoilVolume);
        BuilderUtils.addTag(tags, "lauter_deadspace", lauterDeadSpace);
        BuilderUtils.addTag(tags, "top_up_kettle", topUpKettle);
        BuilderUtils.addTag(tags, "hop_utilization", hopUtilization);
        BuilderUtils.addTag(tags, "notes", notes);

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

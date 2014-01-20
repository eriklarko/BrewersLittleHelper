package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Equipment;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;
import org.blh.core.unit.weight.Kilograms;

/**
 * Builder for the Equipment type.
 *
 * @author thinner
 */
public interface EquipmentBuilder extends Builder<Equipment> {

    EquipmentBuilder setBatchSize(Liters batchSize);

    EquipmentBuilder setBoilSize(Liters boilSize);

    EquipmentBuilder setBoilTime(Minutes boilTime);

    EquipmentBuilder setCalculateBoilVolume(boolean calculateBoilVolume);

    EquipmentBuilder setEvapRate(Percentage evapRate);

    EquipmentBuilder setHopUtilization(Percentage hopUtilization);

    EquipmentBuilder setLauterDeadSpace(Liters lauterDeadSpace);

    EquipmentBuilder setName(String name);

    EquipmentBuilder setNotes(String notes);

    EquipmentBuilder setTopUpKettle(Liters topUpKettle);

    EquipmentBuilder setTopUpWater(Liters topUpWater);

    EquipmentBuilder setTrubChillerLoss(Liters trubChillerLoss);

    EquipmentBuilder setTunSpecificHeat(double tunSpecificHeat);

    EquipmentBuilder setTunVolume(Liters tunVolume);

    EquipmentBuilder setTunWeight(Kilograms tunWeight);
}

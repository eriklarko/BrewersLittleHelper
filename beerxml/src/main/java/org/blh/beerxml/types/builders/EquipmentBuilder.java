/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.Equipment;
import org.blh.core.units.Percentage;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Kilograms;

/**
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

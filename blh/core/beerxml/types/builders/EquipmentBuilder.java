/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Equipment;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public interface EquipmentBuilder extends Builder<Equipment> {

    EquipmentBuilder setBatchSize(Liters batchSize);

    EquipmentBuilder setBoilSize(Liters boilSize);

    EquipmentBuilder setBoilTime(Minutes boilTime);

    EquipmentBuilder setCalculatedBoilVolume(boolean calculatedBoilVolume);

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

package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Equipment;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class EquipmentParser extends RecordSetParser<Equipment> {

    @Override
    protected Equipment parseType(NodeList values) {
        String name = null;
        Liters boilSize = null;
        Liters batchSize = null;
        Liters tunVolume = null;
        Kilograms tunWeight = null;
        double tunSpecificHeat = 0;
        Liters topUpWater = null;
        Liters trubChillerLoss = null;
        Percentage evapRate = null;
        Minutes boilTime = null;
        boolean calculatedBoilVolume = false;
        Liters lauterDeadSpace = null;
        Liters topUpKettle = null;
        Percentage hopUtilization = null;
        String notes = null;


        for (int i = 0; i < values.getLength(); i++) {
            Node valueNode = values.item(i);
            String value = valueNode.getNodeValue();
            switch (valueNode.getNodeName().toUpperCase()) {
                case "NAME":
                    name = value;
                    break;
                case "BOIL_SIZE":
                    boilSize = new Liters(Double.parseDouble(value));
                    break;
                case "BATCH_SIZE":
                    batchSize = new Liters(Double.parseDouble(value));
                    break;
                case "TUN_VOLUME":
                    tunVolume = new Liters(Double.parseDouble(value));
                    break;
                case "TUN_WEIGHT":
                    tunWeight = new Kilograms(Double.parseDouble(value));
                    break;
                case "TUN_SPECIFIC_HEAT":
                    tunSpecificHeat = Double.parseDouble(value);
                    break;
                case "TOP_UP_WATER":
                    topUpWater = new Liters(Double.parseDouble(value));
                    break;
                case "TRUB_CHILLER_LOSS":
                    trubChillerLoss = new Liters(Double.parseDouble(value));
                    break;
                case "EVAP_RATE":
                    evapRate = new Percentage(Double.parseDouble(value));
                    break;
                case "BOIL_TIME":
                    boilTime = new Minutes(Integer.parseInt(value));
                    break;
                case "CALC_BOIL_VOLUME":
                    calculatedBoilVolume = Boolean.parseBoolean(value);
                    break;
                case "LATUER_DEADSPACE":
                    lauterDeadSpace = new Liters(Double.parseDouble(value));
                    break;
                case "TOP_UP_KETTLE":
                    topUpKettle = new Liters(Double.parseDouble(value));
                    break;
                case "HOP_UTILIZATION":
                    hopUtilization = new Percentage(Double.parseDouble(value));
                    break;
                case "NOTES":
                    notes = value;
                    break;
                default:
                    System.out.println("Unknown equipment value: " + value);
                    break;
            }
        }

        return new Equipment(name, boilSize, batchSize, tunVolume, tunWeight, tunSpecificHeat, topUpWater, trubChillerLoss, evapRate, boilTime, calculatedBoilVolume, lauterDeadSpace, topUpKettle, hopUtilization, notes);
    }
}

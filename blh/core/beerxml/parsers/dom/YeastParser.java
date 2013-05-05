package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Yeast;
import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class YeastParser extends RecordSetParser<Yeast> {

    @Override
    protected Yeast parseType(NodeList values) {
        String name = null;
        Yeast.TYPE type = null;
        Yeast.FORM form = null;
        double amount = 0;
        boolean amountIsWeight = false;
        String laboratory = null;
        String productId = null;
        Celcius minTemperature = null;
        Celcius maxTemperature = null;
        Yeast.FLOCCULATION flocculation = null;
        Percentage attenuation = null;
        String notes = null;
        String bestFor = null;
        int timesCultured = 0;
        int maxReuse = 0;
        boolean addToSecondary = false;

        for (int i = 0; i < values.getLength(); i++) {
            Node valueNode = values.item(i);
            String value = valueNode.getNodeValue();

            switch (valueNode.getNodeName().toUpperCase()) {
                case "NAME":
                    name = value;
                    break;
                case "TYPE":
                    type = Yeast.TYPE.valueOf(value.toUpperCase());
                    break;
                case "FORM":
                    form = Yeast.FORM.valueOf(value.toUpperCase());
                    break;
                case "AMOUNT":
                    amount = Double.parseDouble(value);
                    break;
                case "AMOUNT_IS_WEIGHT":
                    amountIsWeight = Boolean.parseBoolean(value);
                    break;
                case "LABORATORY":
                    laboratory = value;
                    break;
                case "PRODUCT_ID":
                    productId = value;
                    break;
                case "MIN_TEMPERATURE":
                    minTemperature = new Celcius(Double.parseDouble(value));
                    break;
                case "MAX_TEMPERATURE":
                    maxTemperature = new Celcius(Double.parseDouble(value));
                    break;
                case "FLOCCULATION":
                    flocculation = Yeast.FLOCCULATION.valueOf(value.toUpperCase());
                    break;
                case "ATTENUATION":
                    attenuation = new Percentage(Double.parseDouble(value));
                    break;
                case "NOTES":
                    notes = value;
                    break;
                case "BEST_FOR":
                    bestFor = value;
                    break;
                case "TIMES_CULTURED":
                    timesCultured = Integer.parseInt(value);
                    break;
                case "MAX_REUSE":
                    maxReuse = Integer.parseInt(value);
                    break;
                case "ADD_TO_SECONDARY":
                    addToSecondary = Boolean.parseBoolean(value);
                    break;
                default:
                    System.out.println("Unknown yeast value: " + valueNode.getNodeName());
                    break;
            }
        }

        return new Yeast(name, type, form, amount, amountIsWeight, laboratory, productId, minTemperature, maxTemperature, flocculation, attenuation, notes, bestFor, timesCultured, maxReuse, addToSecondary);
    }
}

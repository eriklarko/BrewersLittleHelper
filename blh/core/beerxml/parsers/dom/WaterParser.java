package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Water;
import blh.core.units.PH;
import blh.core.units.PPM;
import blh.core.units.volume.Liters;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class WaterParser extends RecordSetParser<Water> {

    @Override
    protected Water parseType(NodeList values) {
        String name = null;
        Liters amount = null;
        PPM calcium = null;
        PPM bicarbonate = null;
        PPM sulfate = null;
        PPM chloride = null;
        PPM sodium = null;
        PPM magnesium = null;
        PH ph = null;
        String notes = null;

        for (int i = 0; i < values.getLength(); i++) {
            Node valueNode = values.item(i);
            String value = valueNode.getNodeValue();

            switch (valueNode.getNodeName().toUpperCase()) {
                case "NAME":
                    name = value;
                    break;
                case "AMOUNT":
                    amount = new Liters(Double.parseDouble(value));
                    break;
                case "CALCIUM":
                    calcium = new PPM(Double.parseDouble(value));
                    break;
                case "BICARBONATE":
                    bicarbonate = new PPM(Double.parseDouble(value));
                    break;
                case "SULFATE":
                    sulfate = new PPM(Double.parseDouble(value));
                    break;
                case "CHLORIDE":
                    chloride = new PPM(Double.parseDouble(value));
                    break;
                case "SODIUM":
                    sodium = new PPM(Double.parseDouble(value));
                    break;
                case "MAGNESIUM":
                    magnesium = new PPM(Double.parseDouble(value));
                    break;
                case "PH":
                    ph = new PH(Double.parseDouble(value));
                    break;
                case "NOTES":
                    notes = value;
                    break;
                default:
                    System.out.println("Unknown water value: " + valueNode.getNodeName());
                    break;
            }

        }

        return new Water(name, amount, calcium, bicarbonate, sulfate, chloride, sodium, magnesium, ph, notes);

    }
}

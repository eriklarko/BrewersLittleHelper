package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Hop;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class HopParser extends RecordSetParser<Hop> {
    
    @Override
    protected Hop parseType(NodeList hopValues) {
        
        String name = null;
        Percentage alpha = null;
        Kilograms amount = null;
        Hop.USE use = null;
        Minutes time = null;
        String notes = null;
        Hop.TYPE type = null;
        Hop.FORM form = null;
        Percentage beta = null;
        Percentage hopStabilityIndex = null;
        String origin = null;
        String substitutes = null;
        Percentage humulene = null;
        Percentage caryophyllene = null;
        Percentage cohumulone = null;
        Percentage myrcene = null;

        for (int i = 0; i < hopValues.getLength(); i++) {
            Node hopValueNode = hopValues.item(i);
            String value = hopValueNode.getNodeValue();

            switch (hopValueNode.getNodeName().toUpperCase()) {
                case "NAME":
                    name = value;
                    break;
                case "ALPHA":
                    alpha = new Percentage(Double.parseDouble(value));
                    break;
                case "AMOUNT":
                    amount = new Kilograms(Double.parseDouble(value));
                    break;
                case "USE":
                    use = Hop.USE.valueOf(value.toUpperCase());
                    break;
                case "TIME":
                    time = new Minutes(Integer.parseInt(value));
                    break;
                case "NOTES":
                    notes = value;
                    break;
                case "TYPE":
                    type = Hop.TYPE.valueOf(value.toUpperCase());
                    break;
                case "FORM":
                    form = Hop.FORM.valueOf(value.toUpperCase());
                    break;
                case "BETA":
                    beta = new Percentage(Double.parseDouble(value));
                    break;
                case "HSI":
                    hopStabilityIndex = new Percentage(Double.parseDouble(value));
                    break;
                case "ORIGIN":
                    origin = value;
                    break;
                case "SUBSTITUTES":
                    substitutes = value;
                    break;
                case "HUMULENE":
                    humulene = new Percentage(Double.parseDouble(value));
                    break;
                case "CARYOPHYLLENE":
                    caryophyllene = new Percentage(Double.parseDouble(value));
                    break;
                case "COHUMULONE":
                    cohumulone = new Percentage(Double.parseDouble(value));
                    break;
                case "MYRCENE":
                    myrcene = new Percentage(Double.parseDouble(value));
                    break;
                default:
                    System.out.println("UNKNOWN HOP VALUE: " + hopValueNode.getNodeName());
                    break;
            }
        }
        return new Hop(name, alpha, amount, use, time, notes, type, form, beta, hopStabilityIndex, origin, substitutes, humulene, caryophyllene, cohumulone, myrcene);
    }

}

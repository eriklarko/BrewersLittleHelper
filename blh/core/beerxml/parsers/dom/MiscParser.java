package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Misc;
import blh.core.units.time.Minutes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class MiscParser extends RecordSetParser<Misc> {

    @Override
    protected Misc parseType(NodeList values) {
        String name = null;
        Misc.TYPE type = null;
        Misc.USE use = null;
        Minutes time = null;
        double amount = 0;
        boolean amountIsWeight = false;
        String useFor = null;
        String notes = null;
        
        for (int i = 0; i < values.getLength(); i++) {
            Node valueNode = values.item(i);
            String value = valueNode.getNodeValue();
            
            switch(valueNode.getNodeName().toUpperCase()) {
                case "NAME":
                    name = value;
                    break;
                case "TYPE":
                    type = Misc.TYPE.valueOf(value.toUpperCase());
                    break;
                case "USE":
                    use = Misc.USE.valueOf(value.toUpperCase());
                    break;
                case "TIME":
                    time = new Minutes(Integer.parseInt(value));
                    break;
                case "AMOUNT":
                    amount = Double.parseDouble(value);
                    break;
                case "AMOUNT_IS_WEIGHT":
                    amountIsWeight = Boolean.parseBoolean(value);
                    break;
                case "USE_FOR":
                    useFor = value;
                    break;
                case "NOTES":
                    notes = value;
                    break;
                default:
                    System.out.println("Unknown misc value: " + valueNode.getNodeName());
                    break;
            }
        }
        
        return new Misc(name, type, use, time, amount, amountIsWeight, useFor, notes);
    }
}

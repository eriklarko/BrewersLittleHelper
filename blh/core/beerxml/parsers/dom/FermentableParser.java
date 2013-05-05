package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.GrainOrAdjunctFermentable;
import blh.core.beerxml.types.LiquidFermentable;
import blh.core.units.Lintner;
import blh.core.units.Percentage;
import blh.core.units.color.Lovibond;
import blh.core.units.color.SRM;
import blh.core.units.weight.Kilograms;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class FermentableParser extends RecordSetParser<Fermentable> {

    @Override
    protected Fermentable parseType(NodeList fermentableValues) {
        String name = null;
        Fermentable.TYPE type = null;
        Kilograms amount = null;
        Percentage yield = null;
        /**
         * Is SRM for liquid extracts, lovibond for the rests
         */
        String colorString = null;
        boolean addAfterBoil = false;
        String origin = null;
        String supplier = null;
        String notes = null;
        Percentage maxInBatch = null;
        double IBUGallonsPerPound = 0;
        Percentage coarseFineDiff = null;
        Percentage moisture = null;
        Lintner diastaticPower = null;
        Percentage protein = null;
        boolean recommendMash = false;

        for (int i = 0; i < fermentableValues.getLength(); i++) {
            Node fermentableNode = fermentableValues.item(i);
            String value = fermentableNode.getNodeValue();

            switch (value.toUpperCase()) {
                case "NAME":
                    name = value;
                    break;
                case "TYPE":
                    type = Fermentable.TYPE.valueOf(value.replace(" ", "_").toUpperCase());
                    break;
                case "AMOUNT":
                    amount = new Kilograms(Double.parseDouble(value));
                    break;
                case "YIELD":
                    yield = new Percentage(Double.parseDouble(value));
                    break;
                case "COLOR":
                    colorString = value;
                    break;
                case "ADD_AFTER_BOIL":
                    addAfterBoil = Boolean.parseBoolean(value);
                    break;
                case "ORIGIN":
                    origin = value;
                    break;
                case "SUPPLIER":
                    supplier = value;
                    break;
                case "NOTES":
                    notes = value;
                    break;
                case "COARSE_FINE_DIFF":
                    coarseFineDiff = new Percentage(Double.parseDouble(value));
                    break;
                case "MOISTURE":
                    moisture = new Percentage(Double.parseDouble(value));
                    break;
                case "DIASTATIC_POWER":
                    diastaticPower = new Lintner(Double.parseDouble(value));
                    break;
                case "PROTEIN":
                    protein = new Percentage(Double.parseDouble(value));
                    break;
                case "MAX_IN_BATCH":
                    maxInBatch = new Percentage(Double.parseDouble(value));
                    break;
                case "RECOMMENDED_MASH":
                    recommendMash = Boolean.parseBoolean(value);
                    break;
                case "IBU_GAL_PER_LB":
                    IBUGallonsPerPound = Double.parseDouble(value);
                    break;
                default:
                    System.out.println("Unknown fermentable value: " + fermentableNode.getNodeName());
                    break;
            }
        }

        Fermentable toReturn;
        if (type.equals(Fermentable.TYPE.EXTRACT)) {
            SRM color = new SRM(Double.parseDouble(colorString));
            toReturn = new LiquidFermentable(name, amount, yield, addAfterBoil, origin, supplier, notes, maxInBatch, color, IBUGallonsPerPound);
        } else if (type.equals(Fermentable.TYPE.ADJUNCT) || type.equals(Fermentable.TYPE.GRAIN)) {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            toReturn = new GrainOrAdjunctFermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch, coarseFineDiff, moisture, diastaticPower, protein, recommendMash);
        } else {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            toReturn = new Fermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);
        }

        return toReturn;

    }
}

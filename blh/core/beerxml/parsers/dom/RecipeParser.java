package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Recipe;
import blh.core.beerxml.types.Style;
import blh.core.beerxml.types.Water;
import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.builders.RecipeBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class RecipeParser extends RecordSetParser<Recipe> {

    private RecipeBuilder builder;
    private RecordSetParser<Style> styleParser;
    private RecordSetParser<Equipment>equipmentParser;
    private RecordSetParser<Hop>hopsParser;
    private RecordSetParser<Fermentable>fermentablesParser;
    private RecordSetParser<Misc>miscsParser;
    private RecordSetParser<Yeast>yeastsParser;
    private RecordSetParser<Water>waterParser;
    private RecordSetParser<MashProfile> mashProfileParser;
    
    public RecipeParser(RecipeBuilder builder) {
        super(builder);
    }

    @Override
    protected Recipe parseType(NodeList values) throws ParseException {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);

            switch (node.getNodeName().toUpperCase()) {
                case "STYLE":
                    builder.setStyle(styleParser.parseType(node.getChildNodes()));
                    break;
                case "EQUIPMENT":
                    builder.setEquipment(equipmentParser.parseType(node.getChildNodes()));
                    break;
                case "HOPS":
                    builder.setHops(hopsParser.parse(node.getChildNodes()).getRecords());
                    break;
                case "FERMENTABLES":
                    builder.setFermentables(fermentablesParser.parse(node.getChildNodes()).getRecords());
                    break;
                case "MISCS":
                    builder.setMiscs(miscsParser.parse(node.getChildNodes()).getRecords());
                    break;
                case "YEASTS":
                    builder.setYeasts(yeastsParser.parse(node.getChildNodes()).getRecords());
                    break;
                case "WATERS":
                    builder.setWaters(waterParser.parse(node.getChildNodes()).getRecords());
                    break;
                case "MASH":
                    builder.setMashProfile(mashProfileParser.parseType(node.getChildNodes()));
                    break;

                default:
                    builder.set(node.getNodeName(), node.getNodeValue());
                    break;
            }
        }
        return builder.create();
    }
}

package org.blh.beerxml.parser.dom;

import org.blh.beerxml.ParseException;
import org.blh.beerxml.type.Equipment;
import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Recipe;
import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Water;
import org.blh.beerxml.type.Yeast;
import org.blh.beerxml.type.builder.RecipeBuilder;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parses the Recipe record set.
 *
 * @author thinner
 */
public class RecipeParser extends RecordSetParser<Recipe> {

    private final RecipeBuilder builder;
    private final RecordSetParser<Style> styleParser;
    private final RecordSetParser<Equipment> equipmentParser;
    private final RecordSetParser<Hop> hopsParser;
    private final RecordSetParser<Fermentable> fermentablesParser;
    private final RecordSetParser<Misc> miscsParser;
    private final RecordSetParser<Yeast> yeastsParser;
    private final RecordSetParser<Water> waterParser;
    private final RecordSetParser<MashProfile> mashProfileParser;

    public RecipeParser(RecipeBuilder builder, RecordSetParser<Style> styleParser,
            RecordSetParser<Equipment> equipmentParser,
            RecordSetParser<Hop> hopsParser, RecordSetParser<Fermentable> fermentablesParser,
            RecordSetParser<Misc> miscsParser, RecordSetParser<Yeast> yeastsParser,
            RecordSetParser<Water> waterParser, RecordSetParser<MashProfile> mashProfileParser) {
        super(builder);
        this.builder = builder;
        this.styleParser = styleParser;
        this.equipmentParser = equipmentParser;
        this.hopsParser = hopsParser;
        this.fermentablesParser = fermentablesParser;
        this.miscsParser = miscsParser;
        this.yeastsParser = yeastsParser;
        this.waterParser = waterParser;
        this.mashProfileParser = mashProfileParser;
    }

    @Override
    public Recipe parseRecord(NodeList values) throws ParseException {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                parseNode(node);
            }
        }
        return builder.create();
    }

    // This is as complex as it needs to be
    private void parseNode(Node node) throws ParseException, DOMException {
        switch (node.getNodeName().toUpperCase()) {
            case "VERSION":
                break;
            case "STYLE":
                builder.setStyle(styleParser.parseRecord(node.getChildNodes()));
                break;
            case "EQUIPMENT":
                builder.setEquipment(equipmentParser.parseRecord(node.getChildNodes()));
                break;
            case "HOPS":
                builder.setHops(hopsParser.parseRecordSet(node.getChildNodes()).getRecords());
                break;
            case "FERMENTABLES":
                builder.setFermentables(fermentablesParser.parseRecordSet(node.getChildNodes()).getRecords());
                break;
            case "MISCS":
                builder.setMiscs(miscsParser.parseRecordSet(node.getChildNodes()).getRecords());
                break;
            case "YEASTS":
                builder.setYeasts(yeastsParser.parseRecordSet(node.getChildNodes()).getRecords());
                break;
            case "WATERS":
                builder.setWaters(waterParser.parseRecordSet(node.getChildNodes()).getRecords());
                break;
            case "MASH":
                builder.setMashProfile(mashProfileParser.parseRecord(node.getChildNodes()));
                break;

            default:
                builder.set(node.getNodeName(), node.getTextContent());
                break;
        }
    }
}

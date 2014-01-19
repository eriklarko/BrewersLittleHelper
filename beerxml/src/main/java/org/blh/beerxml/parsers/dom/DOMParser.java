package org.blh.beerxml.parsers.dom;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.blh.beerxml.BeerXMLParser;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownRecordException;
import org.blh.beerxml.UnknownRecordSetException;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;
import org.blh.beerxml.types.Equipment;
import org.blh.beerxml.types.Fermentable;
import org.blh.beerxml.types.Hop;
import org.blh.beerxml.types.MashProfile;
import org.blh.beerxml.types.MashStep;
import org.blh.beerxml.types.Misc;
import org.blh.beerxml.types.Recipe;
import org.blh.beerxml.types.Style;
import org.blh.beerxml.types.Water;
import org.blh.beerxml.types.Yeast;
import org.blh.beerxml.types.builders.EquipmentBuilderImpl;
import org.blh.beerxml.types.builders.FermentableBuilderImpl;
import org.blh.beerxml.types.builders.HopBuilderImpl;
import org.blh.beerxml.types.builders.MashProfileBuilderImpl;
import org.blh.beerxml.types.builders.MashStepBuilderImpl;
import org.blh.beerxml.types.builders.MiscBuilderImpl;
import org.blh.beerxml.types.builders.RecipeBuilderImpl;
import org.blh.beerxml.types.builders.StyleBuilderImpl;
import org.blh.beerxml.types.builders.WaterBuilderImpl;
import org.blh.beerxml.types.builders.YeastBuilderImpl;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author thinner
 */
public class DOMParser implements BeerXMLParser {

    private final RecordSetParser<Recipe> recipeParser;
    private final RecordSetParser<Style> styleParser;
    private final RecordSetParser<Equipment> equipmentParser;
    private final RecordSetParser<Hop> hopsParser;
    private final RecordSetParser<Fermentable> fermentablesParser;
    private final RecordSetParser<Misc> miscsParser;
    private final RecordSetParser<Yeast> yeastsParser;
    private final RecordSetParser<Water> watersParser;
    private final RecordSetParser<MashStep> mashStepsParser;
    private final RecordSetParser<MashProfile> mashProfileParser;

    public static DOMParser defaultDOMParser() {
        RecordSetParser<Style> styleParser = new RecordSetParser<>(new StyleBuilderImpl());
        RecordSetParser<Equipment> equipmentParser = new RecordSetParser<>(new EquipmentBuilderImpl());
        RecordSetParser<Hop> hopsParser = new RecordSetParser<>(new HopBuilderImpl());
        RecordSetParser<Fermentable> fermentablesParser = new RecordSetParser<>(new FermentableBuilderImpl());
        RecordSetParser<Misc> miscsParser = new RecordSetParser<>(new MiscBuilderImpl());
        RecordSetParser<Yeast> yeastsParser = new RecordSetParser<>(new YeastBuilderImpl());
        RecordSetParser<Water> watersParser = new RecordSetParser<>(new WaterBuilderImpl());
        RecordSetParser<MashStep> mashStepParser = new RecordSetParser<>(new MashStepBuilderImpl());
        RecordSetParser<MashProfile> mashProfileParser = new MashProfileParser(new MashProfileBuilderImpl(), mashStepParser);

        RecordSetParser<Recipe> recipeParser = new RecipeParser(
                new RecipeBuilderImpl(), styleParser, equipmentParser,
                hopsParser, fermentablesParser, miscsParser, yeastsParser,
                watersParser, mashProfileParser
        );

        return new DOMParser(recipeParser,
                styleParser,
                equipmentParser,
                hopsParser,
                fermentablesParser,
                miscsParser,
                yeastsParser,
                watersParser,
                mashStepParser,
                mashProfileParser
        );
    }

    public DOMParser(RecordSetParser<Recipe> recipeParser, RecordSetParser<Style> styleParser, RecordSetParser<Equipment> equipmentParser, RecordSetParser<Hop> hopsParser, RecordSetParser<Fermentable> fermentablesParser, RecordSetParser<Misc> miscsParser, RecordSetParser<Yeast> yeastsParser, RecordSetParser<Water> watersParser, RecordSetParser<MashStep> mashStepsParser, RecordSetParser<MashProfile> mashProfileParser) {
        this.recipeParser = recipeParser;
        this.styleParser = styleParser;
        this.equipmentParser = equipmentParser;
        this.hopsParser = hopsParser;
        this.fermentablesParser = fermentablesParser;
        this.miscsParser = miscsParser;
        this.yeastsParser = yeastsParser;
        this.watersParser = watersParser;
        this.mashStepsParser = mashStepsParser;
        this.mashProfileParser = mashProfileParser;
    }

    @Override
    public List<BeerXMLRecordSet<BeerXMLRecord>> parse(File xmlFile) throws ParseException {
        try {
            DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = bdf.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            if (document.hasChildNodes()) {
                return parseNode(document.getChildNodes());
            } else {
                return new LinkedList<>();
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<BeerXMLRecordSet<BeerXMLRecord>> parseNode(NodeList nodeList) throws UnknownRecordSetException, ParseException {
        List<BeerXMLRecordSet<BeerXMLRecord>> recordSets = new LinkedList<>();

        for (int count = 0; count < nodeList.getLength(); count++) {
            Node node = nodeList.item(count);
            NodeList children = node.getChildNodes();

            // make sure it's element node.
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                BeerXMLRecordSet<BeerXMLRecord> recordSet;
                if (node.getNodeName().toUpperCase().endsWith("S")) {
                    recordSet = marshalSetToParser(node.getNodeName(), children);
                } else {
                    BeerXMLRecord record = marshalRecordToParser(node.getNodeName(), children);
                    Class class_ = record.getClass();
                    recordSet = new BeerXMLRecordSet<>(class_, record);
                }
                recordSets.add(recordSet);
            }
        }

        return recordSets;
    }

    private BeerXMLRecordSet marshalSetToParser(String nodeName, NodeList children) throws ParseException, UnknownRecordSetException {
        switch (nodeName.toUpperCase()) {
            case "RECIPES":
                return recipeParser.parseRecordSet(children);
            case "EQUIPMENTS":
                return equipmentParser.parseRecordSet(children);
            case "STYLES":
                return styleParser.parseRecordSet(children);
            case "HOPS":
                return hopsParser.parseRecordSet(children);
            case "FERMENTABLES":
                return fermentablesParser.parseRecordSet(children);
            case "YEASTS":
                return yeastsParser.parseRecordSet(children);
            case "MISCS":
                return miscsParser.parseRecordSet(children);
            case "WATERS":
                return watersParser.parseRecordSet(children);
            case "MASH_STEPS":
                return mashStepsParser.parseRecordSet(children);
            case "MASHS":
                return mashProfileParser.parseRecordSet(children);
            default:
                throw new UnknownRecordSetException("Unknown record set: " + nodeName);
        }
    }

    private BeerXMLRecord marshalRecordToParser(String nodeName, NodeList values) throws ParseException, UnknownRecordException {
        switch (nodeName.toUpperCase()) {
            case "RECIPE":
                return recipeParser.parseRecord(values);
            case "EQUIPMENT":
                return equipmentParser.parseRecord(values);
            case "STYLE":
                return styleParser.parseRecord(values);
            case "HOP":
                return hopsParser.parseRecord(values);
            case "FERMENTABLE":
                return fermentablesParser.parseRecord(values);
            case "YEAST":
                return yeastsParser.parseRecord(values);
            case "MISC":
                return miscsParser.parseRecord(values);
            case "WATER":
                return watersParser.parseRecord(values);
            case "MASH_STEP":
                return mashStepsParser.parseRecord(values);
            case "MASH":
                return mashProfileParser.parseRecord(values);
            default:
                throw new UnknownRecordException("Unknown record set: " + nodeName);
        }
    }

    private void printNode(NodeList nodeList) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());
                    }
                }

                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    printNode(tempNode.getChildNodes());
                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
            }
        }
    }
}

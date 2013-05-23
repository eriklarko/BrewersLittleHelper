package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.BeerXMLRecordSet;
import blh.core.beerxml.BeerXMLParser;
import blh.core.beerxml.ParseException;
import blh.core.beerxml.UnknownRecordException;
import blh.core.beerxml.UnknownRecordSetException;
import blh.core.beerxml.types.BeerXMLRecord;
import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.MashStep;
import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Recipe;
import blh.core.beerxml.types.Style;
import blh.core.beerxml.types.Water;
import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.builders.EquipmentBuilderImpl;
import blh.core.beerxml.types.builders.FermentableBuilderImpl;
import blh.core.beerxml.types.builders.HopBuilderImpl;
import blh.core.beerxml.types.builders.MashProfileBuilderImpl;
import blh.core.beerxml.types.builders.MashStepBuilderImpl;
import blh.core.beerxml.types.builders.MiscBuilderImpl;
import blh.core.beerxml.types.builders.RecipeBuilderImpl;
import blh.core.beerxml.types.builders.StyleBuilderImpl;
import blh.core.beerxml.types.builders.WaterBuilderImpl;
import blh.core.beerxml.types.builders.YeastBuilderImpl;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

    private RecordSetParser<Recipe> recipeParser;
    private RecordSetParser<Style> styleParser;
    private RecordSetParser<Equipment> equipmentParser;
    private RecordSetParser<Hop> hopsParser;
    private RecordSetParser<Fermentable> fermentablesParser;
    private RecordSetParser<Misc> miscsParser;
    private RecordSetParser<Yeast> yeastsParser;
    private RecordSetParser<Water> watersParser;
    private RecordSetParser<MashStep> mashStepsParser;
    private RecordSetParser<MashProfile> mashProfileParser;

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

        RecordSetParser<? extends BeerXMLRecord> parser;
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

package org.blh.beerxml.parser.dom;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.blh.beerxml.ClassToRecordNameMapper.NoRecordNameException;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.parser.BeerXMLParser;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;
import org.blh.beerxml.type.Equipment;
import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Recipe;
import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Water;
import org.blh.beerxml.type.Yeast;
import org.blh.beerxml.type.builder.EquipmentBuilderImpl;
import org.blh.beerxml.type.builder.FermentableBuilderImpl;
import org.blh.beerxml.type.builder.HopBuilderImpl;
import org.blh.beerxml.type.builder.MashProfileBuilderImpl;
import org.blh.beerxml.type.builder.MashStepBuilderImpl;
import org.blh.beerxml.type.builder.MiscBuilderImpl;
import org.blh.beerxml.type.builder.RecipeBuilderImpl;
import org.blh.beerxml.type.builder.StyleBuilderImpl;
import org.blh.beerxml.type.builder.WaterBuilderImpl;
import org.blh.beerxml.type.builder.YeastBuilderImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Implements the BeerXML parsing using DOM technology, as opposed to SAX or
 * StAX.
 *
 * @author thinner
 */
public class DOMParser implements BeerXMLParser {

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

        return new DOMParserBuilder().setRecipeParser(recipeParser)
                .setStyleParser(styleParser)
                .setEquipmentParser(equipmentParser)
                .setHopsParser(hopsParser)
                .setFermentablesParser(fermentablesParser)
                .setMiscsParser(miscsParser)
                .setYeastsParser(yeastsParser)
                .setWatersParser(watersParser)
                .setMashStepsParser(mashStepParser).setMashProfileParser(mashProfileParser)
                .createDOMParser();
    }

    private final Map<String, RecordSetParser<?>> parsers;

    DOMParser(RecordSetParser<Recipe> recipeParser, RecordSetParser<Style> styleParser,
            RecordSetParser<Equipment> equipmentParser,
            RecordSetParser<Hop> hopsParser, RecordSetParser<Fermentable> fermentablesParser,
            RecordSetParser<Misc> miscsParser, RecordSetParser<Yeast> yeastsParser,
            RecordSetParser<Water> watersParser, RecordSetParser<MashStep> mashStepsParser,
            RecordSetParser<MashProfile> mashProfileParser) {

        parsers = new HashMap<>();
        parsers.put("RECIPE", recipeParser);
        parsers.put("EQUIPMENT", equipmentParser);
        parsers.put("STYLE", styleParser);
        parsers.put("HOP", hopsParser);
        parsers.put("FERMENTABLE", fermentablesParser);
        parsers.put("YEAST", yeastsParser);
        parsers.put("MISC", miscsParser);
        parsers.put("WATER", watersParser);
        parsers.put("MASH_STEP", mashStepsParser);
        parsers.put("MASH", mashProfileParser);
    }

    @Override
    public List<BeerXMLRecordSet<BeerXMLRecord>> parse(File xmlFile) throws ParseException, NoRecordNameException {
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

    private List<BeerXMLRecordSet<BeerXMLRecord>> parseNode(NodeList nodeList) throws NoRecordNameException, ParseException {
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
                    Class clazz = record.getClass();
                    recordSet = new BeerXMLRecordSet<>(clazz, record);
                }
                recordSets.add(recordSet);
            }
        }

        return recordSets;
    }

    private BeerXMLRecordSet marshalSetToParser(String nodeName, NodeList children) throws ParseException, NoRecordNameException {
        return getParser(nodeName).parseRecordSet(children);
    }

    private RecordSetParser<?> getParser(String nodeName) throws NoRecordNameException {
        String key = nodeName.toUpperCase();
        if (key.endsWith("S")) {
            key = key.substring(0, key.length() - 1);
        }
        RecordSetParser<?> recordSetParser = parsers.get(key);
        if (recordSetParser == null) {
            throw new NoRecordNameException("Don't know how to parse " + nodeName + ", no parser with key: " + key);
        }
        return recordSetParser;
    }

    private BeerXMLRecord marshalRecordToParser(String nodeName, NodeList values) throws ParseException, NoRecordNameException {
        return getParser(nodeName).parseRecord(values);
    }

    /*private void printNode(NodeList nodeList) {
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
     }*/
}

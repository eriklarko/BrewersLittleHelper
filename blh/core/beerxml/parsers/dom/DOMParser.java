package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.BeerXMLRecordSet;
import blh.core.beerxml.BeerXMLParser;
import blh.core.beerxml.ParseException;
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
import java.io.File;
import java.io.IOException;
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
    public List<BeerXMLRecordSet> parse(File xmlFile) throws ParseException {
        try {
            DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = bdf.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            if (document.getDocumentElement().hasChildNodes()) {
                return parseNode(document.getDocumentElement().getChildNodes());
            } else {
                return new LinkedList<>();
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    private List<BeerXMLRecordSet> parseNode(NodeList nodeList) throws ParseException {
        List<BeerXMLRecordSet> recordSets = new LinkedList<>();

        for (int count = 0; count < nodeList.getLength(); count++) {
            Node node = nodeList.item(count);
            NodeList children = node.getChildNodes();

            // make sure it's element node.
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                switch (node.getNodeName().toUpperCase()) {
                    case "RECIPES":
                        recordSets.add(recipeParser.parse(children));
                        break;
                    case "EQUIPMENTS":
                        recordSets.add(equipmentParser.parse(children));
                        break;
                    case "STYLES":
                        recordSets.add(styleParser.parse(children));
                        break;
                    case "HOPS":
                        recordSets.add(hopsParser.parse(children));
                        break;
                    case "FERMENTABLES":
                        recordSets.add(fermentablesParser.parse(children));
                        break;
                    case "YEASTS":
                        recordSets.add(yeastsParser.parse(children));
                        break;
                    case "MISCS":
                        recordSets.add(miscsParser.parse(children));
                        break;
                    case "WATERS":
                        recordSets.add(watersParser.parse(children));
                        break;
                    case "MASH_STEPS":
                        recordSets.add(mashStepsParser.parse(children));
                        break;
                    case "MASHS":
                        recordSets.add(mashProfileParser.parse(children));
                        break;
                    default:
                        System.out.println("Unknown node: " + node.getNodeName());
                        break;
                }
            }
        }

        return recordSets;
    }
}

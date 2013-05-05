package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.BeerXMLData;
import blh.core.beerxml.BeerXMLParser;
import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.GrainOrAdjunctFermentable;
import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.LiquidFermentable;
import blh.core.units.Lintner;
import blh.core.units.Percentage;
import blh.core.units.color.Lovibond;
import blh.core.units.color.SRM;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;
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

    @Override
    public BeerXMLData parse(File xmlFile) throws ParseException {
        try {
            DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = bdf.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void printNode(NodeList nodeList) {
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

    private static void parseYeasts(NodeList yeastsRecordSet) {
    }

    private static void parseMiscs(NodeList miscsRecordSet) {
    }

    private static void parseWaters(NodeList watersRecordSet) {
    }

    private static void parseStyles(NodeList stylesRecordSet) {
    }

    private static void parseMashSteps(NodeList mashStepsRecordSet) {
    }

    private static void parseMashs(NodeList mashsRecordSet) {
    }

    private static void parseRecipes(NodeList recipesRecordSet) {
    }

    private static void parseEquipments(NodeList equipmentsRecordSet) {
    }
}

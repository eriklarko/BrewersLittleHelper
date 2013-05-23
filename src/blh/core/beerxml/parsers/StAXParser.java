package blh.core.beerxml.parsers;

import blh.core.beerxml.types.BeerXMLRecordSet;
import blh.core.beerxml.BeerXMLParser;
import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.BeerXMLRecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author thinner
 */
public class StAXParser implements BeerXMLParser {

    @Override
    public List<BeerXMLRecordSet<BeerXMLRecord>> parse(File xmlFile) throws ParseException {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileReader(xmlFile));

            if (reader.getEventType() != XMLStreamConstants.START_ELEMENT) {
                throw new XMLStreamException(
                        "parser must be on START_ELEMENT to read next text", reader.getLocation());
            }

            int eventType = reader.next();
            StringBuilder content = new StringBuilder();
            while (eventType != XMLStreamConstants.END_ELEMENT) {
                if (eventType == XMLStreamConstants.CHARACTERS
                        || eventType == XMLStreamConstants.CDATA
                        || eventType == XMLStreamConstants.SPACE
                        || eventType == XMLStreamConstants.ENTITY_REFERENCE) {
                    content.append(reader.getText());
                } else if (eventType == XMLStreamConstants.PROCESSING_INSTRUCTION
                        || eventType == XMLStreamConstants.COMMENT) {
                    // skipping
                } else if (eventType == XMLStreamConstants.END_DOCUMENT) {
                    throw new XMLStreamException(
                            "unexpected end of document when reading element text content", reader.getLocation());
                } else if (eventType == XMLStreamConstants.START_ELEMENT) {
                    throw new XMLStreamException(
                            "element text content may not contain START_ELEMENT", reader.getLocation());
                } else {
                    throw new XMLStreamException(
                            "Unexpected event type " + eventType, reader.getLocation());
                }
                eventType = reader.next();
            }

            return null;
        } catch (XMLStreamException | FileNotFoundException ex) {
            throw new ParseException(ex);
        }
    }
}
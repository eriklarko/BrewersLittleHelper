package org.blh.beerxml.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;

/**
 * UNFINISHED!
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
            walkTree(reader);
            return null;
        } catch (XMLStreamException | FileNotFoundException ex) {
            throw new ParseException(ex);
        }
    }

    private String walkTree(XMLStreamReader reader) throws XMLStreamException {
        int eventType = reader.next();
        StringBuilder content = new StringBuilder();
        while (eventType != XMLStreamConstants.END_ELEMENT) {
            if (isText(eventType)) {
                content.append(reader.getText());
            } else if (shouldIgnore(eventType)) {
                content.append("");
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

        return content.toString();
    }

    private boolean shouldIgnore(int eventType) {
        return eventType == XMLStreamConstants.PROCESSING_INSTRUCTION
                || eventType == XMLStreamConstants.COMMENT;
    }

    private boolean isText(int eventType) {
        return eventType == XMLStreamConstants.CHARACTERS
                || eventType == XMLStreamConstants.CDATA
                || eventType == XMLStreamConstants.SPACE
                || eventType == XMLStreamConstants.ENTITY_REFERENCE;
    }
}

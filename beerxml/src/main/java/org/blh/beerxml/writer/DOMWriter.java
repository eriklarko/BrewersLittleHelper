package org.blh.beerxml.writer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.blh.beerxml.ClassToRecordNameMapper;
import org.blh.beerxml.ClassToRecordNameMapper.NoRecordNameException;
import org.blh.beerxml.parser.dom.DOMParser;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * A BeerXML writer using DOM technology.
 *
 * @author Erik Larkö
 * @since May 21, 2013 9:23:58 PM
 */
public class DOMWriter implements BeerXMLWriter<BeerXMLRecord> {

    private final ClassToRecordNameMapper recordNameMapper;

    public DOMWriter(ClassToRecordNameMapper recordNameMapper) {
        this.recordNameMapper = recordNameMapper;
    }

    @Override
    public void write(File file, List<BeerXMLRecordSet<BeerXMLRecord>> recordSets) throws IOException, NoRecordNameException {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            for (BeerXMLRecordSet<BeerXMLRecord> recordSet : recordSets) {
                Node recordSetNode = transform(recordSet, doc);
                doc.appendChild(recordSetNode);
            }
            write(file, doc);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Node transform(BeerXMLRecordSet recordSet, Document doc) throws NoRecordNameException {
        List<BeerXMLRecord> records = recordSet.getRecords();
        String recordSetName = this.recordNameMapper.getRecordSetName(recordSet);

        Node recordSetNode = doc.createElement(recordSetName);
        for (BeerXMLRecord record : records) {
            recordSetNode.appendChild(transform(record, doc));
        }

        return recordSetNode;
    }

    private Node transform(BeerXMLRecord record, Document doc) throws NoRecordNameException {
        String recordTag = this.recordNameMapper.getRecordName(record);
        Node recordNode = doc.createElement(recordTag);

        for (Map.Entry<String, String> tagAndValue : record.getBeerXMLTagsAndValues().entrySet()) {
            Node tag = doc.createElement(tagAndValue.getKey());
            Node tagValue = doc.createTextNode(tagAndValue.getValue());
            tag.appendChild(tagValue);

            recordNode.appendChild(tag);
        }

        List<BeerXMLRecordSet> subRecords = record.getSubRecordSets();
        if (subRecords != null) {
            for (BeerXMLRecordSet recordSet : subRecords) {
                recordNode.appendChild(transform(recordSet, doc));
            }
        }

        return recordNode;
    }

    private void write(File file, Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}

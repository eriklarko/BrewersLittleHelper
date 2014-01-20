package org.blh.beerxml.parser.dom;

import java.util.LinkedList;
import java.util.List;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;
import org.blh.beerxml.type.builder.Builder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Wraps a BeerXML record builder, using it to iterate and parse
 * record sets.
 *
 * @author thinner
 */
public class RecordSetParser<T extends BeerXMLRecord> {

    private final Builder<T> builder;

    public RecordSetParser(Builder<T> builder) {
        if (builder == null) {
            throw new NullPointerException("The builder must not be null");
        }
        this.builder = builder;
    }

    public BeerXMLRecordSet<T> parseRecordSet(NodeList recordSet) throws ParseException {
        List<T> types = new LinkedList<>();
        Class clazz = null;
        for (int i = 0; i < recordSet.getLength(); i++) {
            Node node = recordSet.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList values = node.getChildNodes();

                T record = parseRecord(values);
                clazz = record.getClass();
                types.add(record);
            }
        }

        return new BeerXMLRecordSet<>(clazz, types);
    }

    public T parseRecord(NodeList values) throws ParseException {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                builder.set(node.getNodeName(), node.getTextContent());
            }
        }
        return builder.create();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.BeerXMLRecord;
import blh.core.beerxml.types.BeerXMLRecordSet;
import blh.core.beerxml.types.builders.Builder;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class RecordSetParser<T extends BeerXMLRecord> {

    private Builder<T> builder;

    public RecordSetParser(Builder<T> builder) {
        if (builder == null) {
            throw new NullPointerException("The builder must not be null");
        }
        this.builder = builder;
    }

    public BeerXMLRecordSet<T> parseRecordSet(NodeList recordSet) throws ParseException {
        List<T> types = new LinkedList<>();
        Class class_ = null;
        for (int i = 0; i < recordSet.getLength(); i++) {
            Node node = recordSet.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList values = node.getChildNodes();

                T record = parseRecord(values);
                class_ = record.getClass();
                types.add(record);
            }
        }

        //Class class_ = (Class<?>) (((ParameterizedType)RecordSetParser.class.getGenericSuperclass()).getActualTypeArguments()[0]);
        return new BeerXMLRecordSet<>(class_, types);
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

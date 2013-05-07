/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.builders.Builder;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class RecordSetParser<T> {

    private Builder<T> builder;

    public RecordSetParser(Builder<T> builder) {
        if (builder == null) {
            throw new NullPointerException("The builder must not be null");
        }
        this.builder = builder;
    }

    public List<T> parse(NodeList recordSet) {
        List<T> types = new LinkedList<>();
        for (int i = 0; i < recordSet.getLength(); i++) {
            Node node = recordSet.item(i);
            NodeList values = node.getChildNodes();
            types.add(parseType(values));
        }

        return types;
    }

    protected T parseType(NodeList values) {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);
            builder.set(node.getNodeName(), node.getNodeValue());
        }
        return builder.create();
    }
}

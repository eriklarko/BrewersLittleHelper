/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.Hop;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public abstract class RecordSetParser<T> {
    
    public List<T> parse(NodeList recordSet) {
        List<T> types = new LinkedList<>();
        for (int i = 0; i < recordSet.getLength(); i++) {
            Node node = recordSet.item(i);
            NodeList values = node.getChildNodes();
            types.add(parseType(values));
        }

        return types;
    }
    protected abstract T parseType(NodeList values);
}
